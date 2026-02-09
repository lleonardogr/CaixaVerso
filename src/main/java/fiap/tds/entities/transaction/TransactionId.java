package fiap.tds.entities.transaction;

import java.util.UUID;

public record TransactionId(UUID id) {
    public static TransactionId newId(){return new TransactionId(UUID.randomUUID());}
    public static TransactionId parse(String s){return new TransactionId(UUID.fromString(s));}
    @Override public String toString(){return id.toString();}
}
