package fiap.tds.entities.transaction;

import fiap.tds.entities.account.AccountId;
import fiap.tds.entities.valueObjects.Money;

import java.time.Instant;
import java.util.Objects;

public record TransferIn(TransactionId id,
                         Instant at,
                         Money amount,
                         String category,
                         String description,
                         AccountId from) implements Transaction
{
    public TransferIn{
        Objects.requireNonNull(id);
        Objects.requireNonNull(at);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(category);
        Objects.requireNonNull(description);
        Objects.requireNonNull(from);
    }
    @Override public Money delta(){return amount;}
}
