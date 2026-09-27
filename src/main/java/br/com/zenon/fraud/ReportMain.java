package br.com.zenon.fraud;

import br.com.zenon.fraud.TransactionReport.Statistic;
public class ReportMain {

    public static void main(String[] args) {
        var transactionReport = new TransactionReport();
        Statistic statistic = transactionReport.generateReport("data/PS_20174392719_1491204439457_log.csv");
        System.out.println("Total linhas: " + statistic.totalTransaction());
        System.out.println(" Total de fraudes:  " + statistic.totalFrauds());
        System.out.println(" Total de amount:  " + statistic.totalAmount());

    }
}
