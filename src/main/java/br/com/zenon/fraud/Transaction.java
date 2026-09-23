package br.com.zenon.fraud;


import java.math.BigDecimal;

public record Transaction (
        int step, TypePayment type, BigDecimal amount,TransactionCustomer origin, TransactionCustomerDest recipientDest,
        boolean isFraud, boolean isFlaggedFraud) {


}
