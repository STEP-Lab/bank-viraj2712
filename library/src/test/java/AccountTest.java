import com.thoughtworks.bank.Account;
import com.thoughtworks.bank.InvalidAccountNumberException;
import com.thoughtworks.bank.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws MinimumBalanceException, InvalidAccountNumberException {
        account = new Account("1111-2222",2000);
    }

    @Test
    public void checkAccountNumber() {
        assertThat(account.getAccountNumber(),is("1111-2222"));
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void checkInvalidAccountNumber() throws MinimumBalanceException, InvalidAccountNumberException {
        Account account1 = new Account("ab12=34cd",2000);
    }

    @Test
    public void checkBalance() {
        assertThat(account.getBalance(),is(2000.0));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance() throws MinimumBalanceException, InvalidAccountNumberException {
        new Account("2222-1111", 500);
    }

    @Test
    public void validateCreditTransaction() throws MinimumBalanceException {
        account.credit(1000);
        assertThat(account.getBalance(),is(3000.0));
    }

    @Test(expected = MinimumBalanceException.class)
    public void validateDeclinedCredit() throws MinimumBalanceException {
        account.credit(-500);
    }

    @Test
    public void validateDebitTransaction() throws MinimumBalanceException {
        account.debit(500);
        assertThat(account.getBalance(),is(1500.0));
    }

    @Test(expected = MinimumBalanceException.class)
    public void validateDeclinedDebit() throws MinimumBalanceException {
        account.debit(1500);
    }
}
