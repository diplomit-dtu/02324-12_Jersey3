package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.User;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
	static Map<Long, User> users = new HashMap<Long, User>();
	static Long nextId= 0L;
	private static Long getNextId() {
		return nextId++;
	}
	//Insert some dummy data
	static {
		Long newId = getNextId();
		users.put(newId, new User(newId, "rapAnd", "andeby"));
	}
	
	@POST
	public User createNewUser(User user){
		Long newId = getNextId();
		user.setUserId(newId);
		users.put(newId, user);
		return user;
	}
	


	@GET
	public List<User> getAllUsers(){
		return new ArrayList<User>(users.values());
	}
	
	@GET
	@Path("{id}")
	public User getOneUser(@PathParam("id") String id){
		return users.get(id);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteUser(@PathParam("id") Long id){
		users.remove(id);
	}
	

}
