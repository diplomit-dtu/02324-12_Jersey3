package rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidIdMapper implements ExceptionMapper<InvalidIdException> {
    @Override
    public Response toResponse(InvalidIdException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST) //Status kode 400
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
