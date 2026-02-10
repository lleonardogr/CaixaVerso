import fiap.tds.Bank;
import fiap.tds.entities.account.Account;
import fiap.tds.entities.account.AccountId;
import fiap.tds.entities.transaction.Deposit;
import fiap.tds.entities.transaction.TransactionId;
import fiap.tds.entities.transaction.Withdrawl;
import fiap.tds.entities.valueObjects.Money;
import fiap.tds.services.TxFmt;

void main(String[] args){
    IO.println("Sistema do CaixaVerso - comandos: help, exit");
    var bank  = new Bank();

    while(true){
        String line = IO.readln("> ").trim();
        if(line.isEmpty()) continue;
        if(line.equalsIgnoreCase("exit")) return;

        try{
            if(line.equalsIgnoreCase("help")){
                IO.println("""
                        new <owner> <limit> [initialBalance] - cria nova conta
                        list - lista contas
                        deposit <accId> <amount> <category> <description> - deposita valor
                        withdraw <accId> <amount> <category> <description> - sacar valor
                        balance <accId> - mostrar saldo
                        last <accId> <n> - mostrar útimas n transações
                        find <accId> <txId> - mostrar detalhes das transação
                        sum <addId> - somar as ultimas operações
                        """);
                continue;
            }

            var parts = line.split("\\s+", 5);
            var cmd = parts[0];

            switch(cmd){
                case "new" -> {
                    var p = line.split("\\s+");
                    if(p.length < 3) throw new IllegalArgumentException("Nova conta: new <owner> <limit> [init]");
                    var id = bank.newAccount(p[1], p[2], (p.length >= 4 ? p[3] : null));
                    IO.println("Conta criada: " + id);
                }
                case "list" -> bank.listAccounts();
            }
        }
        catch (Exception e) {
            IO.println("Erro: " + e.getMessage());
        }
    }
}
