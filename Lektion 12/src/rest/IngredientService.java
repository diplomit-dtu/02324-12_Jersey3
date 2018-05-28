package rest;

import dto.Ingredient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("ingredient")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IngredientService {
	static Map<Integer, Ingredient> ingredients = new HashMap<>();
	//Insert some dummy data
	static {
		ingredients.put(1, new Ingredient(1, "flormelis", 60.0));
		ingredients.put(2, new Ingredient(2, "mel", 240.0));
		ingredients.put(3, new Ingredient(3, "smør", 185.0));
	} 
    
    @GET
    public List<Ingredient> getIngredientList() {
        return new ArrayList<>(ingredients.values());
    }

    @GET
    @Path("{id}")
    public Ingredient getIngredient(@PathParam("id") int id) {
        return ingredients.get(id);
    }
    
    @POST
    public Ingredient addIngredientJson(Ingredient ingredient) throws InvalidIdException {
        if (ingredients.putIfAbsent(ingredient.getId(), ingredient) != null) {

            //Throw homemade InvalidIdException
        	throw new InvalidIdException("ID " + ingredient.getId() + " er allerede i brug");        	

//        	Response response = Response
//        			.status(Status.BAD_REQUEST)
//        			.entity("Id " + ingredient.getId() + " er i brug")
//        			.build();
//
//        	//Throw WebApplicationException
//        	throw new WebApplicationException(response);

//        	//Return response
//        	return response;

        }

        return ingredient;
    }
    
    @DELETE
    @Path("{id}")
    public void deleteIngredient(@PathParam("id") int id) {
        ingredients.remove(id);
    }
}
