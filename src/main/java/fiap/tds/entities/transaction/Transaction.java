package fiap.tds.entities.transaction;

import fiap.tds.entities.valueObjects.Money;

import java.time.Instant;


public sealed interface Transaction permits Deposit, Withdrawl, TransferIn, TransferOut {
    TransactionId id();
    Instant at();
    Money amount();
    String category();
    String description();
    Money delta();
}

