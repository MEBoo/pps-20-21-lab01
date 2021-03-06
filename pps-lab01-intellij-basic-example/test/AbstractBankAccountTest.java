import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractBankAccountTest {
    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;

    protected final double initialBalance=0;
    protected final double depositAmount=100;
    protected final double withdrawAmount=70;

    abstract void setupBankAccount();

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        setupBankAccount();
    }

    @Test
    void testInitialBalance() {
        assertEquals(initialBalance, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), depositAmount);
        assertEquals(initialBalance+depositAmount, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), depositAmount);
        bankAccount.deposit(2, depositAmount);
        assertEquals(initialBalance+depositAmount, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), depositAmount);
        bankAccount.withdraw(accountHolder.getId(), withdrawAmount);
        assertEquals(initialBalance+depositAmount-withdrawAmount, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), depositAmount);
        bankAccount.withdraw(2, withdrawAmount);
        assertEquals(initialBalance+depositAmount, bankAccount.getBalance());
    }
}
