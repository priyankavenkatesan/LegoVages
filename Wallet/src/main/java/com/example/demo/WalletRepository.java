package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class
 * 
 * @author Priyanka
 *
 */
@Repository
@Transactional
public interface WalletRepository extends JpaRepository<Wallet, String> {

	@Query(value = "select * from wallet w where w.player_Id= ? order by w.updated_time desc", nativeQuery = true)
	public List<Wallet> findByLatestBalance(String playerId);

	public List<Wallet> findByPlayerId(String playerId);

}
