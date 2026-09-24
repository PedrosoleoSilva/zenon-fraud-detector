package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TransactionIngestor {

    public List<Transaction> TransactionsList(String file) {
        Path path = Path.of(file);
        try {
            List<String> lines = Files.readAllLines(path);
                 return  lines.stream().skip(1).limit(1000)
                         .map(this::ParseTransaction).toList();

        }catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + file, e);
        }
    }

    public Transaction ParseTransaction(String linha) {
        String[] collums = linha.split(",");
        int step = Integer.parseInt(collums[0]);
        TypePayment type = TypePayment.valueOf(collums[1]);
        BigDecimal amount = new BigDecimal(collums[2]);

        TransactionCustomer transactionCustomer = new TransactionCustomer(
                collums[3],
                new BigDecimal(collums[4]),
                new BigDecimal(collums[5])
        );
        TransactionCustomerDest transactionCustomerDest = new TransactionCustomerDest(
                collums[6],
                new BigDecimal(collums[7]),
                new BigDecimal(collums[8])
        );
        boolean isFraud = collums[9].equals("1");

        boolean isFlaggedFraud = collums[10].equals("1");

        return new Transaction(
                step,
                type,
                amount,
                transactionCustomer,
                transactionCustomerDest,
                isFraud,
                isFlaggedFraud
        );
    }


}
