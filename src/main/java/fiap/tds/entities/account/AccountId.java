package fiap.tds.entities.account;

import java.util.UUID;

public record AccountId(UUID id) {
    // Gera um novo Id unico
    public static AccountId newId(){return new AccountId(UUID.randomUUID());}

    //A partir de um texto, ele converte em um id
    public static AccountId parse(String s){return new AccountId(UUID.fromString(s));}

    // Apresenta o UUID como string
    @Override public String toString(){return id.toString();}
}
