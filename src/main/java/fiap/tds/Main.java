import fiap.tds.entities.account.Account;
import fiap.tds.entities.account.AccountId;
import fiap.tds.entities.transaction.Deposit;
import fiap.tds.entities.transaction.TransactionId;
import fiap.tds.entities.transaction.Withdrawl;
import fiap.tds.entities.valueObjects.Money;
import fiap.tds.services.TxFmt;

long saldo = 0;
void main(){
    IO.println("Sistema do CaixaVerso: depósito <v>, Saques <v>, Saldo e Sair");
    var newAcc = new Account(AccountId.newId(), "Léo", Money.zero(), Money.parseSigned("1000.00"));
    //newAcc.apply(new Deposit(TransactionId.newId(), Instant.now(),
            //Money.parsePositive("10.00"), "PIX", "pagamento de salário"));
    //newAcc.apply(new Withdrawl(TransactionId.newId(), Instant.now(), Money.parsePositive("3.50"), "SAQUE", "pagamento de contas"));
    IO.println("saldo=" + newAcc.balance());
}
