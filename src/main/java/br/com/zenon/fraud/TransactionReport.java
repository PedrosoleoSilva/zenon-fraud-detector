package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionReport {

    private record ReportTransaction(BigDecimal amount, boolean isFraud) {

    }

    public  record Statistic(long totalTransaction, long totalFrauds, BigDecimal totalAmount){

        private static   Statistic add(Statistic acc, ReportTransaction rt ){
            return new Statistic(
                    acc.totalTransaction + 1,
                    acc.totalFrauds + (rt.isFraud ? 1 : 0),
                    acc.totalAmount.add(rt.amount));
        }
    }

    public Statistic generateReport(String filename){
        Path path = Path.of(filename);
        try (Stream<String> lines = Files.lines(path)) {
           return lines.skip(1)
                    .map(this::ParseReportTransaction)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .reduce(
                            new Statistic(0,0,BigDecimal.ZERO),
                            Statistic::add, (s1, s2) -> s1);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + filename, e);
        }
    }

    public Optional<ReportTransaction> ParseReportTransaction(String linha) {
        try {
            String[] collums = linha.split(",");

            if (collums[2] == null | collums[2].trim().isEmpty()) throw new IllegalArgumentException("amount nao pode ser null");
            BigDecimal amount = new BigDecimal(collums[2]);

            boolean isFraud = collums[9].equals("1");

            return Optional.of(new ReportTransaction(amount, isFraud));
        } catch (Exception e) {
            System.err.println("Erro ao ParseTransaction: " + linha + "|" + e);
        }
        return Optional.empty();
    }
}
