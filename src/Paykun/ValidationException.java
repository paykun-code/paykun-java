package Paykun;

public class ValidationException extends Throwable{
	
	private String field; 
    
	public ValidationException(String message, String code, String field)
    {
    	super(message);

    	this.field = field;
    }

    public String getField()
    {
        return this.field;
    }
}
