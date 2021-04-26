DROP table if exists wallet;

CREATE TABLE wallet (
  transaction_id INT PRIMARY KEY,
  player_id VARCHAR(250) NOT NULL,
  balance DOUBLE (20),
  updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO wallet (transaction_id, player_id, balance) VALUES(1,'Leo', 1000.0);
INSERT INTO wallet (transaction_id, player_id, balance) VALUES(2,'vages', 500.50);