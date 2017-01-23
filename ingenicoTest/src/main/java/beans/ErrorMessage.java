package beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean to generate JSON error messages.
 * @author ElenaM
 *
 */
public class ErrorMessage {

	@JsonProperty("errorMessage")
	private String errorMessage;
	
	public ErrorMessage (String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
