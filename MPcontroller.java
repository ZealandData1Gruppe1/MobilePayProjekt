import java.util.ArrayList;

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
    public void sendPenge(Bruger afsender, Bruger modtager, double amount)
    {

    }
    public void visAnmodninger(Bruger b)
    {

    }
    public void godkendAnmodning(Transaktion t)
    {
        sendPenge(t.getAfsender(),t.getModtager(),t.getAmount());
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
