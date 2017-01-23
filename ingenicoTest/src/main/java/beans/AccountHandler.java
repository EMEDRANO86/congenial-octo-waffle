package beans;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores account information and handle account transactions.
 * @author ElenaM
 *
 */
public class AccountHandler {
	/**
	 * Map Object containing accounts information.
	 */
	private Map <String,Account> accounts;
	
	public AccountHandler() {
		this.accounts=new HashMap<String,Account>();
	}
	
	/**
	 * Creates a new account after checking the Account Id does not already exists.
	 * @param accountId String containing the account id for the new account.
	 * @param balance double containing the initial amount for the new account.
	 * @return boolean indicating the transaction result.
	 */
	public boolean addAccount (String accountId, double balance) {
		Account acc = new Account(accountId, balance);
		if (!accounts.containsKey(accountId)) {
			accounts.put(accountId, acc);
			return true;
		}
		else return false;
	}
	
	/**
	 * Generates the account information of a specific Account Id.
	 * @param accountId String containing the account id.
	 * @return Account object with account information.
	 */
	public Account getAccount(String accountId) {
		return accounts.get(accountId);
	}
	
	/**
	 * Performs transfers of a given amount between two given accounts.
	 * @param sourceAccountId String containing the Source Account Id.
	 * @param targetAccountId String containing the Target Account Id.
	 * @param amount double containing the amount to be transferred from source account to target.
	 * @return String containing information about the transaction result.
	 */
	public String transferAmount (String sourceAccountId,String targetAccountId, double balance) {
		if (accounts.containsKey(sourceAccountId)) {
			if (accounts.containsKey(targetAccountId)) {
				double sourceBalance = accounts.get(sourceAccountId).getBalance();
				if (sourceBalance >= balance) {
					double targetBalance = accounts.get(targetAccountId).getBalance();
					accounts.replace(sourceAccountId, new Account(sourceAccountId, sourceBalance-balance));
					accounts.replace(targetAccountId, new Account(targetAccountId, targetBalance+balance));
					return "OK";
				} else return "Balance";
			}
			else return "Target";
		}
		else return "Source";
	}
}
