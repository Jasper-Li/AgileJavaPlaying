package sis.studentinfo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Account {
    private int transactionCount = 0;
    public BigDecimal getBalance() {
        return balance;
    }

    BigDecimal balance = new BigDecimal("0.00");
    public void addCredit(BigDecimal credit) {
        balance = balance.add(credit);
        ++transactionCount;
    }

    public BigDecimal getTransactionAverage() {
        return balance.divide(new BigDecimal(transactionCount), RoundingMode.HALF_UP);
    }
}
