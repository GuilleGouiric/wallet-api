CREATE TABLE IF NOT EXISTS wallets (
    id BIGSERIAL PRIMARY KEY,
    owner_id BIGINT NOT NULL,
    balance NUMERIC(15,2) NOT NULL,
    description VARCHAR(255) NULL,
    date_created DATE NOT NULL DEFAULT CURRENT_DATE
);

INSERT INTO wallets (id, owner_id, balance, description, date_created)
VALUES
    (1, 1, 10000.00, 'Deposit Sink Wallet', '2025-05-12'),
    (2, 2, 0.00, 'Withdraw Sink Wallet', '2025-05-12'),
    (3, 3, 0.00, 'Test Wallet for historical', '2024-05-12'),
    (4, 4, 0.00, 'Test Wallet for historical', '2024-05-12')
    ON CONFLICT (id) DO NOTHING;

SELECT setval(pg_get_serial_sequence('wallets', 'id'), 5, false);

CREATE TABLE IF NOT EXISTS transfers (
    id BIGSERIAL PRIMARY KEY,
    source_id BIGINT NOT NULL,
    target_id BIGINT NOT NULL,
    amount NUMERIC(15, 2) NOT NULL,
    date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    type VARCHAR(50) NOT NULL
);

INSERT INTO transfers (id, source_id, target_id, amount, date_created, type)
VALUES
  (1, 10, 3, 250.00, '2024-06-12 10:00:00', 'DEPOSIT'),
  (2, 10, 4, 500.00, '2024-06-12 10:15:00', 'DEPOSIT'),
  (3, 10, 3, 1000.00, '2024-07-12 10:30:00', 'DEPOSIT'),
  (4, 3, 4, 50.00, '2024-08-04 11:00:00', 'TRANSFER'),
  (5, 3, 10, 30.00, '2024-08-15 11:15:00', 'WITHDRAW'),
  (6, 4, 3, 100.00, '2025-01-12 11:30:00', 'TRANSFER'),
  (7, 3, 4, 75.00, '2025-05-11 12:00:00', 'TRANSFER'),
  (8, 3, 4, 60.00, '2025-05-11 12:30:00', 'TRANSFER')
  ON CONFLICT (id) DO NOTHING;


SELECT setval(pg_get_serial_sequence('transfers', 'id'), 9, false);