package rest;

@SuppressWarnings("serial")
public class InvalidIdException extends Exception  {

	public InvalidIdException(){
		super("ID er allerede i brug");
	}

	public InvalidIdException(String message){
		super(message);
	}


}
