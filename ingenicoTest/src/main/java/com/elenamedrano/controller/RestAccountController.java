package com.elenamedrano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import beans.AccountHandler;
import beans.ErrorMessage;

/**
 * RestController to handle and resolve REST API requests.
 * @author ElenaM
 *
 */
@RestController
public class RestAccountController {
	
	
	/**
	 * AccountHandler object containing all the existing accounts.
	 */
	@Autowired
	AccountHandler accountHandler;
	
	/**
	 * Requests for new account creation are redirected to this method.
	 * @param accountId String containing the id for the new account. Must be unique.
	 * @param balance double containing the initial amount for the new account.
	 * @return ResponseEntity<String> containing the information about the transaction result.
	 */
	@RequestMapping(value="/account/accountId/{accountId}/balance/{balance}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> createAccount(@PathVariable("accountId") String accountId, @PathVariable("balance") double balance) {
		if (accountHandler.addAccount(accountId, balance)==true) return new ResponseEntity<String>(HttpStatus.OK); 
		else {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			ErrorMessage em = new ErrorMessage("The account already exists");
			String json="";
			try {
				json = ow.writeValueAsString(em);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);		
		}
	}
	
	/**
	 * Requests for amount transfers between accounts are redirected to this method.
	 * @param sourceAccountId String containing the Source Account Id.
	 * @param targetAccountId String containing the Target Account Id.
	 * @param amount double containing the amount to be transferred from source account to target.
	 * @return ResponseEntity<String> containing the information about the transaction result.
	 */
	@RequestMapping(value="/transfer/sourceAccountId/{sourceAccountId}/targetAccountId/{targetAccountId}/amount/{amount}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> transferAmount(@PathVariable("sourceAccountId") String sourceAccountId,@PathVariable("targetAccountId") String targetAccountId, @PathVariable("amount") double amount) {
		if (accountHandler.transferAmount(sourceAccountId,targetAccountId, amount)=="OK") {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json="";
			try {
				json = ow.writeValueAsString(accountHandler.getAccount(sourceAccountId))+","+ow.writeValueAsString(accountHandler.getAccount(targetAccountId));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<String>(json, HttpStatus.OK); 
		}
		else if (accountHandler.transferAmount(sourceAccountId,targetAccountId, amount)=="Balance"){
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			ErrorMessage em = new ErrorMessage("There is not enough balance for the transaction.");
			String json="";
			try {
				json = ow.writeValueAsString(em);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);		
		}
		else if (accountHandler.transferAmount(sourceAccountId,targetAccountId, amount)=="Target"){
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			ErrorMessage em = new ErrorMessage("The target account does not exist.");
			String json="";
			try {
				json = ow.writeValueAsString(em);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);		
		}
		else if (accountHandler.transferAmount(sourceAccountId,targetAccountId, amount)=="Source"){
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			ErrorMessage em = new ErrorMessage("The source account does not exist.");
			String json="";
			try {
				json = ow.writeValueAsString(em);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);		
		}
		else return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
}
