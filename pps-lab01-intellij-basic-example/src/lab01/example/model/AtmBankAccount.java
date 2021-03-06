package lab01.example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdraw is allowed only if the balance greater or equal the withdrawal amount
 */
public class AtmBankAccount extends AbstractBankAccount {

    private final double fee=1;
    
    public AtmBankAccount(final AccountHolder holder, final double balance) {
        super(holder,balance);
    }

    @Override
    public double getTransactionFee() { return this.fee; }
}
