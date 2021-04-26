package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WalletServiceImpl implements WalletService {
	@Autowired
	WalletRepository walletRepository;

	/**
	 * Method to get all transactions
	 */
	@Override
	public List<WalletDTO> getTransactionHistory() {

		List<Wallet> transactions = walletRepository.findAll();
		List<WalletDTO> history = new ArrayList<>();
		if (transactions.size() != 0) {
			for (Wallet transaction : transactions) {
				history.add(new WalletDTO(transaction));
			}
		}
		return history;
	}

	/**
	 * Method to get transactions per player
	 */
	@Override
	public List<WalletDTO> getTransactionHistoryPerPlayer(String playerId) {
		List<Wallet> transactions = walletRepository.findByPlayerId(playerId);
		List<WalletDTO> history = new ArrayList<>();
		if (transactions.size() != 0) {
			for (Wallet transaction : transactions) {
				history.add(new WalletDTO(transaction));
			}
		}
		return history;
	}

	/**
	 * Method to retrieve the current balance
	 */
	@Override
	public double getLatestBalance(String playerId) {
		List<Wallet> wallets = walletRepository.findByLatestBalance(playerId);
		return wallets.get(0).getBalance();

	}

	@Override
	public void saveTransaction(int transactionId, String playerId, double balance) {
		Wallet wallet = new Wallet(transactionId, playerId, balance);
		walletRepository.save(wallet);

	}

}
