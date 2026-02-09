package fiap.tds.entities.transaction;

import fiap.tds.entities.valueObjects.Money;

import java.time.Instant;
import java.util.Objects;

public record Withdrawl(TransactionId id,
                        Instant at,
                        Money amount,
                        String category,
                        String description) implements Transaction
{
    public Withdrawl{
        Objects.requireNonNull(id);
        Objects.requireNonNull(at);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(category);
        Objects.requireNonNull(description);
    }
    @Override public Money delta(){return amount.negate();}
}
