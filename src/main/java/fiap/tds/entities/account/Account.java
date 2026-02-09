package fiap.tds.entities.account;

import fiap.tds.entities.transaction.Deposit;
import fiap.tds.entities.transaction.Transaction;
import fiap.tds.entities.transaction.TransactionId;
import fiap.tds.entities.transaction.Withdrawl;
import fiap.tds.entities.valueObjects.Money;

import java.time.Instant;
import java.util.*;

import static java.util.Objects.requireNonNull;

public final class Account {
    private final AccountId id;
    private final String owner;
    private final Money overdraftLimit;

    private Money balance;

    private final List<Transaction> history = new ArrayList<>();
    private final Map<TransactionId, Transaction> byId = new HashMap<>();

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

    public TransactionId deposit(String amount, String category, String description){
        var tx = new Deposit(TransactionId.newId(), Instant.now(), Money.parsePositive(amount), category, description);
        apply(tx);
        return tx.id();
    }

    public TransactionId withdraw(String amount, String category, String description) {
        var tx = new Withdrawl(TransactionId.newId(), Instant.now(), Money.parsePositive(amount), category, description);
        apply(tx);
        return tx.id();
    }

    public Optional<Transaction> find(Transaction id){
        return Optional.ofNullable(byId.get(id));
    }

    List<Transaction> last(int n){
        int size = history.size();
        int from = Math.max(0, size - n);
        return List.copyOf(history.subList(from, size));
    }

    Map<String, Money> sumByCategory(){
        var sums = new HashMap<String, Money>();
        for(var t: history)
            sums.merge(t.category(), t.delta(), Money::plus);
        return  Map.copyOf(sums);
    }

    private void apply(Transaction t){
        requireNonNull(t);

        if(byId.containsKey(t.id()))
            throw new IllegalArgumentException("transação dupicada, id=" + t.id());

        var newBalance = balance.plus(t.delta());
        if(newBalance.compareTo(overdraftLimit.negate()) < 0)
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transação (limite estourado)");
        balance = newBalance;

        history.add(t);
        byId.put(t.id(), t);
    }
}
