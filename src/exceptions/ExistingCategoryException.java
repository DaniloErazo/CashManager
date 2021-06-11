package exceptions;

@SuppressWarnings("serial")
public class ExistingCategoryException extends Exception {
	
	
	public ExistingCategoryException(String account) {
		super("The category identified with the name " + account + " of this type already exists in the container");
	}
	
	



}
