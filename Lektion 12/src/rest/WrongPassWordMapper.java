package rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WrongPassWordMapper implements ExceptionMapper<WrongPasswordException> {
	public Response toResponse(WrongPasswordException ex) {
		return Response.status(401).
				entity(ex.getMessage()).
				type("text/plain").
				build();
	}
}

