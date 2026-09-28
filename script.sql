use zenon_frauds;



CREATE table transaction (
                             id bigint auto_increment primary key,
                             step INT NOT NULL,
                             type VARCHAR(20) NOT NULL,
                             amount DECIMAL(20,2),
                             name_origin VARCHAR(50) NOT NULL,
                             old_balance_origin DECIMAL(20,2),
                             new_balance_origin DECIMAL(20,2),
                             name_recipient VARCHAR(50) NOT NULL,
                             old_balance_recipient DECIMAL(20,2),
                             new_balance_recipient DECIMAL(20,2),
                             is_fraud TINYINT(1) DEFAULT 0,
                             is_flagged_fraud TINYINT(1) DEFAULT 0
);

INSERT into transaction ( `step`, type, amount, name_origin, old_balance_origin, new_balance_origin, name_recipient, old_balance_recipient, new_balance_recipient, is_fraud, is_flagged_fraud)
VALUES (1,"PAYMENT",5000.00,"C1000001",5000.00,0.00,"M1000001",0.00,0.00,0,0);

SELECT * FROM transaction;