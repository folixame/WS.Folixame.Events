package ws.folixame.events.webservices;

@SuppressWarnings("serial")
public class DatabaseException extends Exception {
	
	private String faultCode;
	private String faultString;
	
	public DatabaseException(String faultCode){
		super("DatabaseException - "+faultCode);
		this.setFaultString(getMessage()); 
		this.setFaultCode(faultCode);  
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getFaultString() {
		return faultString;
	}

	public void setFaultString(String faultString) {
		this.faultString = faultString;
	}
}
