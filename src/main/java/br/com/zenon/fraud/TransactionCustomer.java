package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public record TransactionCustomer( String name, BigDecimal oldBalance,
                                   BigDecimal newBalance) {

    public TransactionCustomer{
        Objects.requireNonNull(name, "name nao pode ser null");
        Objects.requireNonNull(oldBalance, "oldBalance nao pode ser null");
        Objects.requireNonNull(newBalance, "newBalance nao pode ser null");

        if(oldBalance.signum() < 0) throw new IllegalArgumentException("amount nao pode ser negativo: " + oldBalance);
        if(newBalance.signum() < 0) throw  new IllegalArgumentException("newBalance não pode ser negativo: " + newBalance);
    }
}
