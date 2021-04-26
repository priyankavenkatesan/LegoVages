package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Boot application class
 * 
 * @author Priyanka
 *
 */
@Controller
@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class WalletApplication {
	@Autowired
	WalletService walletService;

	/**
	 * Method to get the balance per player
	 *
	 * @param playerId
	 * @return
	 */
	@RequestMapping(value = "/getBalance", method = RequestMethod.GET)
	@ResponseBody
	public double currentBalance(@RequestParam String playerId) {
		double balance = walletService.getLatestBalance(playerId);

		return balance;
	}

	/**
	 * Method to get transaction history per player
	 * 
	 * @param playerId
	 * @return
	 */
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	@ResponseBody
	public List<WalletDTO> transactionHistory(@RequestParam String playerId) {
		List<WalletDTO> history = walletService.getTransactionHistoryPerPlayer(playerId);

		return history;
	}

	/**
	 * Method to credit the balance per transaction id
	 * 
	 * @param transactionId
	 * @param creditAmount
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/credit", method = RequestMethod.GET)
	@ResponseBody
	public double credit(@RequestParam int transactionId, @RequestParam int creditAmount, @RequestParam String playerId)
			throws Exception {
		// validating transaction id
		boolean validId = validateTransactionId(transactionId, playerId);
		double balance = 0.0;
		if (validId) {
			balance = walletService.getLatestBalance(playerId);
			balance = balance + creditAmount;
			walletService.saveTransaction(transactionId, playerId, balance);
		}

		return balance;
	}

	/**
	 * Method to debit the balance per transaction id
	 * 
	 * @param transactionId
	 * @param debitAmount
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/debit", method = RequestMethod.GET)
	@ResponseBody
	public double debit(@RequestParam int transactionId, @RequestParam int debitAmount, @RequestParam String playerId)
			throws Exception {
		// validating transaction id
		boolean validId = validateTransactionId(transactionId, playerId);
		double balance = 0.0;
		if (validId) {
			balance = walletService.getLatestBalance(playerId);
			if (debitAmount <= balance && balance > 0) {
				balance = balance - debitAmount;
				walletService.saveTransaction(transactionId, playerId, balance);
			} else {
				throw new Exception("Not sufficient Balance");
			}
		}
		return balance;
	}

	/**
	 * Method to validate transaction id
	 * 
	 * @param transactionId
	 * @param playerId
	 * @throws Exception
	 */
	private boolean validateTransactionId(int transactionId, String playerId) throws Exception {
		boolean validID = false;
		List<WalletDTO> history = walletService.getTransactionHistory();
		for (WalletDTO walletDTO : history) {
			if (transactionId != walletDTO.getTransactionId()) {
				validID = true;
			} else {
				validID = false;
			}
		}
		if (!validID) {
			throw new Exception("Invalid Transaction id");
		}
		return validID;
	}

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}

}
