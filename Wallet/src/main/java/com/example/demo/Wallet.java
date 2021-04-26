package com.example.demo;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity class for table Wallet
 * 
 * @author Priyanka
 *
 */
@Entity
@Table(name = "Wallet")
public class Wallet {

	@Id
	@Column(name = "transaction_id")
	private int transactionId;

	@Column(name = "player_id")
	private String playerId;

	@Column(name = "balance")
	private double balance;

	@Column(name = "updated_time")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	public Wallet() {
		super();
	}

	public Wallet(int transactionId, String playerId, double balance) {

		this.transactionId = transactionId;
		this.playerId = playerId;
		this.balance = balance;
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

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}
