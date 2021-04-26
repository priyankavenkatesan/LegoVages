package com.example.demo;

public class WalletDTO {
	private int transactionId;
	private String playerId;
	private double balance;

	public WalletDTO() {

	}

	public WalletDTO(Wallet wallet) {

		this.transactionId = wallet.getTransactionId();
		this.playerId = wallet.getPlayerId();
		this.balance = wallet.getBalance();
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
