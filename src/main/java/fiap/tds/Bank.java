package fiap.tds;

import fiap.tds.entities.account.Account;
import fiap.tds.entities.account.AccountId;
import fiap.tds.entities.valueObjects.Money;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Bank {
    private final Map<AccountId, Account> accounts = new HashMap<>();

    public AccountId newAccount(String owner, String limit, String initialBalance){
        var lim = Money.parseSigned(limit);
        if(lim.isNegative()) throw new IllegalArgumentException("Limite da conta não pode ser negativo");

        var init = (initialBalance == null) ? Money.zero()
                : Money.parseSigned(initialBalance);

        var acc = new Account(AccountId.newId(), owner, init, lim);
        accounts.put(acc.id(), acc);
        return  acc.id();
    }

    public Optional<Account> getAccount(AccountId id){
        return Optional.ofNullable(accounts.get(id));
    }

    public void listAccounts(){
        if(accounts.isEmpty()) {IO.println("{Sem contas cadastradas}"); return;}
        accounts.values().forEach(a -> IO.println("%s Owner =%s saldo=%s limite=%s"
                .formatted(a.id(), a.owner(), a.balance(), a.overdraftLimit())));
    }
}
