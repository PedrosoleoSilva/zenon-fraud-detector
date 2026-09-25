package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public record TransactionCustomerDest( String  name, BigDecimal ldBalanceDest, BigDecimal newBalanceDest) {

    public TransactionCustomerDest {
        Objects.requireNonNull(name, "name nao pode ser null");
        Objects.requireNonNull(ldBalanceDest, "ldBalanceDest nao pode ser null");
        Objects.requireNonNull(newBalanceDest, "newBalance nao pode ser null");

        if(ldBalanceDest.signum() < 0) throw new IllegalArgumentException("amount nao pode ser negativo: " + ldBalanceDest);
        if(newBalanceDest.signum() < 0) throw  new IllegalArgumentException("newBalance não pode ser negativo: " + newBalanceDest);
    }


}
