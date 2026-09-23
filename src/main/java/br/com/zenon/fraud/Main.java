package br.com.zenon.fraud;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
         var transaction = new Transaction( 1, TypePayment.PAYMENT, new BigDecimal("24222.0"),
         new TransactionCustomer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")),
         new TransactionCustomerDest("M1979787155", BigDecimal.ZERO, BigDecimal.ZERO), false, false);

         var transactionDuo = new Transaction( 743, TypePayment.CASH_OUT, new BigDecimal("850002.52"),
                 new TransactionCustomer("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0")),
                 new TransactionCustomerDest("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63")), true, false);

         System.out.println(transaction);
         System.out.println("----------------------");
         System.out.println(transactionDuo);
     }

}
