package br.com.zenon.fraud;

import java.util.Optional;

public interface TransactionRepository {
    void save(Transaction t);
    Optional<Transaction> TransactionsName(String name);


}
