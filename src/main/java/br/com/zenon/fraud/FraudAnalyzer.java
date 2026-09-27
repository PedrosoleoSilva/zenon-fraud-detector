package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FraudAnalyzer {


    private List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactionIsFraudList) {
        this.transactions = transactionIsFraudList;
    }

    public long isFraudTotal() {
        return transactions.stream().filter(Transaction::isFraud)
                .count();
    }
    public List<Transaction> isFraudMaiores(){
      return   transactions.stream().filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount))
              .toList().reversed();

    }

    public List<Transaction> isFraudClientTopFive() {
      return   transactions.stream().filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed()).distinct().toList();
    }
    public BigDecimal isFraudTotalValor() {
        return transactions.stream().filter(Transaction::isFraud)
                .map(Transaction -> Transaction.amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public Map<TypePayment, Long> isFraudTotalPorTransaction() {
        return transactions.stream().filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }

}
