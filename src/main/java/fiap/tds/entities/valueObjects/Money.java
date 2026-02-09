package fiap.tds.entities.valueObjects;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Money pertence aos inteiros
// Conversões para 2 casas decimais
public record Money(long cents) implements Comparable<Money>{
    static Money ofCents(long c){return new Money(c);}

    // Arredondamento bancário positivo, 2 casas
    public static Money parsePositive(String amount){
        var bd = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
        long cents = bd.movePointRight(2).longValueExact();
        if(cents <= 0)
            throw new IllegalArgumentException("Quantidade precisa ser positiva");
        return new Money(cents);
    }

    // Arredondamento bancário, 2 casas
    public static Money parseSigned(String amount){
        var bd = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
        long cents = bd.movePointRight(2).longValueExact();
        return new Money(cents);
    }

    public Money plus (Money other) {return new Money(Math.addExact(this.cents, other.cents));}
    public Money minus (Money other) {return new Money(Math.subtractExact(this.cents, other.cents));}
    public Money negate() {return new Money(Math.negateExact(this.cents));}

    public boolean isNegative(){return cents < 0;}
    public static Money zero() {return new Money(0);}

    // Como está usada a interface Comparable, é necessário um
    // maneira de comparação que valide se 2 números são iguais
    @Override
    public int compareTo(Money o){ return Long.compare(this.cents, o.cents);}

    @Override
    public String toString() {
        return "R$ " + BigDecimal.valueOf(cents, 2);
    }
}
