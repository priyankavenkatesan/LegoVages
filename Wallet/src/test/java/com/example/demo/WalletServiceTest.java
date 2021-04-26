package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

@SpringBootTest
class WalletServiceTest {
	@Autowired
	WalletServiceImpl walletServiceImpl;

	@Autowired
	WalletRepository walletRepository;

	@Test
	void testServices() {

		String playerId = "vages";
		int transactionId = 1;
		double balance = 100;

		walletServiceImpl.saveTransaction(transactionId, playerId, balance);

		double newBalance = walletServiceImpl.getLatestBalance(playerId);

		assertNotNull(newBalance);
		assertEquals(balance, newBalance);

		List<WalletDTO> transactions = walletServiceImpl.getTransactionHistoryPerPlayer(playerId);
		assertTrue(transactions.size() > 0);
		assertEquals(playerId, transactions.get(0).getPlayerId());
	}

}
