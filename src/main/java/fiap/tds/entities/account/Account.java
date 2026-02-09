package fiap.tds.entities.account;

import fiap.tds.entities.transaction.Transaction;
import fiap.tds.entities.valueObjects.Money;

import static java.util.Objects.requireNonNull;

public final class Account {
    private final AccountId id;
    private final String owner;
    private final Money overdraftLimit;

    private Money balance;

    public Account(AccountId id, String owner, Money initialBalance, Money overdraftLimit) {
        this.id = requireNonNull(id);
        this.owner = requireNonNull(owner);
        this.overdraftLimit = requireNonNull(overdraftLimit);
        this.balance =  requireNonNull(initialBalance);

        if(overdraftLimit.isNegative()) throw new IllegalArgumentException("Limite da conta não pode ser negativo");
        if(balance.compareTo(overdraftLimit.negate()) < 0)
            throw new IllegalArgumentException("Saldo inicial vola o limite");
    }

    public AccountId id(){return id;}
    public String owner(){return owner;}
    public Money balance(){return balance;}
    public Money overdraftLimit(){return overdraftLimit;}

    public void apply(Transaction t){
        var newBalance = balance.plus(t.delta());
        if(newBalance.compareTo(overdraftLimit.negate()) < 0)
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transação (limite estourado)");
        balance = newBalance;
    }
}
