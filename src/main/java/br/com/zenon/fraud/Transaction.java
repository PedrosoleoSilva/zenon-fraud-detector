package br.com.zenon.fraud;


import java.math.BigDecimal;
import java.util.Objects;

public record Transaction (
        int step, TypePayment type, BigDecimal amount,TransactionCustomer origin, TransactionCustomerDest recipientDest,
        boolean isFraud, boolean isFlaggedFraud) {


    public Transaction {

        Objects.requireNonNull(type, "type nao pode ser null");
        Objects.requireNonNull(amount, "amount nao pode ser null");


        if(step <= 0) throw new IllegalArgumentException("step nao pode ser negativo: " + step);
        if(amount.signum() <= 0) throw new IllegalArgumentException("amount nao pode ser negativo: " + amount.signum());
    }
}
