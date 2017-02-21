package rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.UserPass;

@Path("password")
public class PasswordService {
	@Context
	UriInfo uriInfo;
	@Context
	HttpHeaders headers;
	@Context
	HttpServletRequest request;

	@POST
	public Boolean testPassword(String userPass){
		System.out.println(userPass);
		boolean returnString = false;
		if (userPass!= null){
			String[] userPassArray = userPass.split(" ");
			if (userPassArray.length==2){
				returnString = testUserAndPassBoolean(userPassArray[0], userPassArray[1]);
			} else {
				returnString = false;
			}
		}
		return returnString;
	}

	//With WebapplicationException
	@Path("query")
	@POST
	public String testQueryPassword(
			@QueryParam("username") String username, 
			@QueryParam("password") String password){
			if (username==null || password == null){
				throw new WebApplicationException("username and/or password missing", Status.BAD_REQUEST);
			}
		return testUserAndPass(username, password);
	}
	//With ExceptionMapper
	@Path("{username}/{password}")
	@POST
	public String testPathPassword(
			@PathParam("username") String username,
			@PathParam("password") String password) 
			throws WrongPasswordException{
		if(testUserAndPassBoolean(username, password)){
			return "Korrekt kodeord";
		} else {
			throw new WrongPasswordException("Forkert kodeord");
		}
	}
	//With ResponseBuilder
	@Path("json")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response testJSONPassword(UserPass userPass){
		System.out.println(userPass);
		if (userPass==null){
			return Response.status(Status.BAD_REQUEST).entity("No credentials in request").build();
		} else if (userPass.getPassword()==null ||userPass.getuserName()==null){
			ObjectMapper mapper = new ObjectMapper(); 
			try {
				return Response.status(Status.BAD_REQUEST).entity("insufficient parameters" + mapper.writeValueAsString(userPass)).build();
			} catch (JsonProcessingException e) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Unable to parse JSON - object: " + userPass).build();
			}
		}
		return Response.ok().build();
	}
	
	//Storing user login status in Http-Session
	@POST
	@Path("form")
	public Boolean testPasswordForm(@FormParam("username") String username, @FormParam("password") String password){
		System.out.println(username + password);
		boolean success = testUserAndPassBoolean(username, password);
		if (success){
			request.getSession().setAttribute("login", new Boolean(true));
		}
		return success;
	}
	//Testing if Session Attribute is set
	@Path("status")
	@GET
	public String testLoginStatus(){
		System.out.println(getSession());
		return (getSession().getAttribute("user")==null)? "Bruger ikke logget ind":"Bruger " +
		getSession().getAttribute("login") + " is logged in";
	}

	private String testUserAndPass(String username, String password) {
		if (username==null || password==null) return "parameter mangler";
		return ("test".equals(password) && "test".equals(username)) ?
				"Passwordet er korrekt" : "Passwordet er forkert";
	}

	private boolean testUserAndPassBoolean(String username, String password) {
		return ("test".equals(password) && "test".equals(username));
	}


	private HttpSession getSession() {
		return request.getSession();
	}

}
