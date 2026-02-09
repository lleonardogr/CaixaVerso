package fiap.tds.services;

import fiap.tds.entities.transaction.*;

public final class TxFmt {
    public static String format(Transaction t){
        return switch (t){
          case Deposit d ->
                  "[DEP] + %s :: %s :: %s :: %s ::  id= %s".formatted(d.amount(), d.category(), d.description(), d.at(), d.id());
          case Withdrawl w ->
                  "[WHI] + %s :: %s :: %s :: %s ::  id= %s".formatted(w.amount(), w.category(), w.description(), w.at(), w.id());
          case TransferOut to ->
                  "[TOU] + %s :: %s :: %s :: %s ::  id= %s to=%s".formatted(to.amount(), to.category(), to.description(), to.at(), to.id(), to.to());
          case TransferIn ti ->
                  "[TIN] + %s :: %s :: %s :: %s ::  id= %s from=%s".formatted(ti.amount(), ti.category(), ti.description(), ti.at(), ti.id(), ti.from());
        };
    }
}
