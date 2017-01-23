package beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean containing Account information (Account id, Balance).
 * @author ElenaM
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
	@JsonProperty("accountId")
	private String accountId;
	
	@JsonProperty("balance")
	private double balance;
	
	public Account (String accountId, double balance) {
		this.accountId = accountId;
		this.balance = balance;
	}
	
	public String getId() {
		return accountId;
	}
	public void setId(String accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	} 
}
