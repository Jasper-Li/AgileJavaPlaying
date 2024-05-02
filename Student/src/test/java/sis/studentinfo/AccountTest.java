package sis.studentinfo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    @Test
    void transactions() {
        var account = new Account();
        account.addCredit(new BigDecimal("0.10"));
        account.addCredit(new BigDecimal("11.0"));
        assertEquals(new BigDecimal("11.10"), account.getBalance());

        account.addCredit(new BigDecimal("2.99"));
        assertEquals(new BigDecimal("4.70"), account.getTransactionAverage());
    }
}
