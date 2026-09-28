package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionSQLRepository implements TransactionRepository {
    @Override
    public void save(Transaction t) {
        String sql = """
                INSERT into transaction (step, `type`, amount, name_origin, old_balance_origin, new_balance_origin, 
                name_recipient, old_balance_recipient, new_balance_recipient, is_fraud, is_flagged_fraud)
                VALUES (?,?,?,?,?,?,?,?,?,?,?);
                """;

        try (java.sql.Connection connection = Connection.getConnection();
              PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, t.step());
            ps.setString(2, t.type().name());
            ps.setBigDecimal(3, t.amount());
            ps.setString(4, t.origin().name());
            ps.setBigDecimal(5, t.origin().oldBalance());
            ps.setBigDecimal(6, t.origin().newBalance());
            ps.setString(7, t.recipientDest().name());
            ps.setBigDecimal(8, t.recipientDest().ldBalanceDest());
            ps.setBigDecimal(9, t.recipientDest().newBalanceDest());
            ps.setBoolean(10, t.isFraud());
            ps.setBoolean(11, t.isFraud());

            ps.execute();

        }catch(SQLException e){
            throw new RuntimeException("Erro ao fazer Insert no banco! " + e);
        }
    }

    @Override
    public Optional<Transaction> TransactionsName(String name) {
        String sql = """
                SELECT id, step, `type`, amount, name_origin, old_balance_origin, new_balance_origin, name_recipient,
                 old_balance_recipient, new_balance_recipient, is_fraud, is_flagged_fraud
                 FROM zenon_frauds.transaction
                 WHERE name_origin = ?
                LIMIT 1;
                """;

        try ( java.sql.Connection connection = Connection.getConnection();
              PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, name);

            try(java.sql.ResultSet rs = ps.executeQuery()){
               if(rs.next()){
                   Transaction transaction = mapResultSetToTransaction(rs);
                   return Optional.of(transaction);
                }else {
                    System.out.println("Nenhuma transacao foi encontrada");
                   return Optional.empty();
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Transaction mapResultSetToTransaction(ResultSet rs) {
        try {
            int step = rs.getInt("step");
            TypePayment type = TypePayment.valueOf(rs.getString("type"));
            BigDecimal amount = rs.getBigDecimal("amount");

            String nameOrigin = rs.getString("name_origin");
            BigDecimal oldBalance = rs.getBigDecimal("old_balance_origin");
            BigDecimal newBalance = rs.getBigDecimal("new_balance_origin");
            TransactionCustomer customer = new TransactionCustomer(nameOrigin, oldBalance, newBalance );

            String name_recipient = rs.getString("name_recipient");
            BigDecimal old_balance_recipient = rs.getBigDecimal("old_balance_origin");
            BigDecimal new_balance_recipient = rs.getBigDecimal("new_balance_origin");
            TransactionCustomerDest customerDest = new TransactionCustomerDest(name_recipient, old_balance_recipient, new_balance_recipient);

            boolean isFraud = rs.getBoolean("is_fraud");
            boolean isFlaggedFraud = rs.getBoolean("is_flagged_fraud");

            return new Transaction(step, type, amount, customer, customerDest, isFraud, isFlaggedFraud);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
