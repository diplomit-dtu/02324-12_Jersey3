package rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@SuppressWarnings("serial")
@Provider
public class InvalidIdException extends WebApplicationException implements ExceptionMapper<InvalidIdException> {

	public InvalidIdException(){
		super("ID er allerede i brug");
	}

	public InvalidIdException(String message){
		super(message);
	}

	@Override
	public Response toResponse(InvalidIdException exception) {
		return Response
				.status(Status.BAD_REQUEST)
				.entity(exception.getMessage())
				.type(MediaType.TEXT_PLAIN)
				.build();
	}

}
