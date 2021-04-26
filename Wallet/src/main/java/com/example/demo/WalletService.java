package com.example.demo;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {

	public List<WalletDTO> getTransactionHistory();
	
	public List<WalletDTO> getTransactionHistoryPerPlayer(String playerId);

	public void saveTransaction(int transactionId, String playerId, double balance);

	public double getLatestBalance(String playerId);

}
