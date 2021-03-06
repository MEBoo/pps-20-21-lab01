package lab01.example.model;

public class AbstractBankAccount implements BankAccount {
    protected final AccountHolder holder;
    protected double balance;
    private final double fee=0;

    public AbstractBankAccount(final AccountHolder holder, final double balance) {
        this.balance = balance;
        this.holder = holder;
    }

    @Override
    public AccountHolder getHolder() {
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
    
    @Override
    public double getTransactionFee() { return this.fee; }

    @Override
    public void deposit(final int usrID, final double amount) {
        if (checkUser(usrID)) {
            this.balance += amount-this.getTransactionFee();
        }
    }

    @Override
    public void withdraw(final int usrID, final double amount) {
        if (checkUser(usrID) && isWithdrawAllowed(amount)) {
            this.balance -= amount+this.getTransactionFee();
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
        return this.balance >= amount+this.getTransactionFee();
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }
}
