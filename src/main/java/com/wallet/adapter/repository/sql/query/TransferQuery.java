package com.wallet.adapter.repository.sql.query;

public class TransferQuery {

    public static final String GET_HISTORICAL_BALANCE_QUERY =
            " SELECT" +
            " COALESCE(SUM(CASE WHEN target_id = :walletId THEN amount ELSE 0 END), 0)" +
            " -" +
            " COALESCE(SUM(CASE WHEN source_id = :walletId THEN amount ELSE 0 END), 0)" +
            " FROM transfers" +
            " WHERE date_created <= :date";
}
