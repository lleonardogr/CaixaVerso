package fiap.tds.entities.transaction;

import fiap.tds.entities.account.AccountId;
import fiap.tds.entities.valueObjects.Money;

import java.time.Instant;
import java.util.Objects;

public record TransferOut(TransactionId id,
                          Instant at,
                          Money amount,
                          String category,
                          String description,
                          AccountId to) implements Transaction
{
    public TransferOut{
        Objects.requireNonNull(id);
        Objects.requireNonNull(at);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(category);
        Objects.requireNonNull(description);
        Objects.requireNonNull(to);
    }
    @Override public Money delta(){return amount.negate();}
}
