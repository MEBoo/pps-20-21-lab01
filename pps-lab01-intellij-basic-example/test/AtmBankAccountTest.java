import lab01.example.model.AtmBankAccount;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */

class AtmBankAccountTest extends AbstractBankAccountTest {
    
    @Override
    void setupBankAccount(){
        bankAccount = new AtmBankAccount(accountHolder, initialBalance);
    }
}
