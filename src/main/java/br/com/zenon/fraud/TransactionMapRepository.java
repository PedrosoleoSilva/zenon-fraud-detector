package br.com.zenon.fraud;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> transactionMap;

    public TransactionMapRepository(List<Transaction> transaction) {
        Objects.requireNonNull(transaction);

        this.transactionMap =
                transaction.stream()
                        .collect(
                                Collectors.toMap(transaction1 -> transaction1.origin().name(), Function.identity()));

    }

    @Override
    public Optional<Transaction> TransactionsName(String name) {
        return Optional.ofNullable(transactionMap.get(name));
    }
}
