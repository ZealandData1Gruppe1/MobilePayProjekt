import java.util.ArrayList;
import java.util.Date;

public class MPcontroller {
    private DBSQL dbsql = new DBSQL();
    private Bruger aktivBruger;


    public MPcontroller(DBSQL dbsql) {
        this.dbsql = dbsql;
    }
    public MPcontroller()
    {

    }

    public void godkendTransaktion(Transaktion t)
    {

    }
    public Bruger hentBruger(String telefonNR)
    {
        return new Bruger();
    }
    public void opretBruger(Bruger b)
    {

    }
    public void addBankKonto(Bruger b, String kontoNR)
    {

    }
    public boolean eksistererBruger(Bruger b)
    {
        return true;
    }
    public void sendPenge(Bruger afsender, Bruger modtager, double amount, String kommentar, Date dato)
    {

        double afsenderBalance = afsender.getB().getBalance();
        if (afsenderBalance < amount)
            System.out.println("Der er ikke nok penge på kontoen");
        else
            afsenderBalance-= amount;
            //Send opdater databasen.

        double modtagersBalance = modtager.getB().getBalance();
        modtagersBalance += amount;
        //Opdatere databasen

        Transaktion nyTransaktion = new Transaktion(afsender,modtager,amount,dato,kommentar);
        //Opdater databasen
    }
    public void visAnmodninger(Bruger b)
    {

    }
    public void godkendAnmodning(Transaktion t)
    {
        
    }

    public void logUd()
    {
       //skal lukke programmet
    }
    public Bruger getAktivBruger() {
        return aktivBruger;
    }

    public void setAktivBruger(Bruger aktivBruger) {
        this.aktivBruger = aktivBruger;
    }
}
