package business.exceptions;

public class ExceptionMessage {
	int  id;
	String message;
	
	public String getMessage(){
		return this.message;
	}
	
	public void setMessage(String msg){
		this.message = msg;
	}
	
	public ExceptionMessage(String msg){
		this.message = msg;
	}
}
