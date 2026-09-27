package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {
         var transaction = new Transaction( 1, TypePayment.PAYMENT, new BigDecimal("24222.0"),
         new TransactionCustomer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")),
         new TransactionCustomerDest("M1979787155", BigDecimal.ZERO, BigDecimal.ZERO), false, false);

         var transactionDuo = new Transaction( 743, TypePayment.CASH_OUT, new BigDecimal("850002.52"),
                 new TransactionCustomer("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0")),
                 new TransactionCustomerDest("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63")), true, false);

        var transactionIngestor = new TransactionIngestor();
      /*   System.out.println(transaction);
         System.out.println("----------------------");
         System.out.println(transactionDuo);


        List<Transaction> transactions = transactionIngestor.TransactionsList("data/PS_20174392719_1491204439457_log.csv");
        System.out.println(transactions.size());
        System.out.println("---------Imprimir as 10 primeiras linhas------------");
        transactions.stream().limit(10).forEach(System.out::println);


        System.out.println("----------------------");
        System.out.println("Identificando erro e fazendo tratamento");
        List<Transaction> transactionBadErro = transactionIngestor.TransactionsList("data/paysim_with_bad_data.csv");
        System.out.println(transactionBadErro.size());
        transactionBadErro.forEach(System.out::println);

*/
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("tarefa/05-streams");
        List<Transaction> transactionIsFraudList = transactionIngestor.TransactionsIsFraud("data/PS_20174392719_1491204439457_log.csv");
        FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(transactionIsFraudList);
        System.out.println(" Total de Fraudes: " + fraudAnalyzer.isFraudTotal());

        List<Transaction> fraudMaiores = fraudAnalyzer.isFraudMaiores();
        System.out.println("Top 3 Fraudes de Maior Valor: ");
        fraudMaiores.stream().map(Transaction::amount).limit(3).forEach(System.out::println);

        List<Transaction> isFraudFiveCliente = fraudAnalyzer.isFraudClientTopFive();
        System.out.println("Top 5 Fraudes de Five Cliente: ");
        isFraudFiveCliente.stream().map(Transaction -> Transaction.origin().name()).limit(5).forEach(System.out::println);

        System.out.println("Prejuízo Total: ");
        System.out.println(fraudAnalyzer.isFraudTotalValor());

        System.out.println("Fraudes por tipos: ");
        System.out.println(fraudAnalyzer.isFraudTotalPorTransaction());
    }


}
