package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.List;

public class DBMain {
    public static void main(String[] args) {
        Connection.getConnection();
        System.out.println("Conexao com Banco de Dados");

        var Repository = new TransactionSQLRepository();
        Repository.TransactionsName("C1000001")
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("Transacao nao encontrada"));

     /*   var t1 = new Transaction( 1, TypePayment.PAYMENT, new BigDecimal("24222.0"),
                new TransactionCustomer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")),
                new TransactionCustomerDest("M1979787155", BigDecimal.ZERO, BigDecimal.ZERO), false, false);

        Repository.save(t1);
        System.out.println("Transacao salvo com sucesso");

      */
        var transactionIngestor = new TransactionIngestor();
        List<Transaction> transactions = transactionIngestor.TransactionsList("data/PS_20174392719_1491204439457_log.csv");
        System.out.println(transactions.size());
        System.out.println("----------------------");


        Long startTimeSQL = System.nanoTime();
        transactions.forEach(Repository::save);
        Long endTimeSQL = System.nanoTime();
        System.out.println("Tempo de transacao no SQL em ms: " + (endTimeSQL - startTimeSQL) / 1_000_000.0);

    }
}
