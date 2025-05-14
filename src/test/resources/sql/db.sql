DELETE FROM wallets;
INSERT INTO wallets (owner_id, balance, description, date_created) VALUES
    (1, 10000.00, 'Deposit Sink Wallet', '2025-05-12'),
    (2, 0.00, 'Withdraw Sink Wallet', '2025-05-12'),
    (3, 0.00, 'Test Wallet for historical', '2024-05-12'),
    (4, 0.00, 'Test Wallet for historical', '2024-05-12');

ALTER TABLE wallets ALTER COLUMN id RESTART WITH 5;

DELETE FROM transfers;
INSERT INTO transfers (source_id, target_id, amount, date_created, type) VALUES
   (10, 3, 250.00, '2024-06-12 10:00:00', 'DEPOSIT'),
   (10, 4, 500.00, '2024-06-12 10:15:00', 'DEPOSIT'),
   (10, 3, 1000.00, '2024-07-12 10:30:00', 'DEPOSIT'),
   (3, 4, 50.00, '2024-08-04 11:00:00', 'TRANSFER'),
   (3, 10, 30.00, '2024-08-15 11:15:00', 'WITHDRAW'),
   (4, 3, 100.00, '2025-01-12 11:30:00', 'TRANSFER'),
   (3, 4, 75.00, '2025-05-11 12:00:00', 'TRANSFER'),
   (3, 4, 60.00, '2025-05-11 12:30:00', 'TRANSFER');

ALTER TABLE transfers ALTER COLUMN id RESTART WITH 9;