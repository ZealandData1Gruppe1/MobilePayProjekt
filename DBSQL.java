import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBSQL {
    private Connection connection;
    private Statement stmt;
    private Statement stmt1;

    DBSQL() {
        connection = null;
        stmt = null;
        try {
            String url = "jdbc:sqlite:C:/sqlite/studerende.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void withdrawMoney(Bruger b, int beløb)
    {

    }
    public void depositMoney(Bruger b, int beløb)
    {

    }
    public void opretTransaktion(Transaktion t)
    {

    }
    public void opretPerson(Bruger b)
    {

    }
    public void opretVirksomhed(Bruger b)
    {

    }
    public ArrayList<Transaktion> hentHistorik(int brugerID)
    {

        return new ArrayList<Transaktion>();
    }
    public ArrayList<Transaktion> hentAnmodninger(int brugerID)
    {

        return new ArrayList<Transaktion>();
    }
    public Bruger hentVirksomhed(String VirksomhedsNR)
    {
        return new Bruger();
    }
    public Bruger hentPerson(String telefonNR)
    {
        return new Bruger();
    }
    public int findBankKonto(int kontoNR)
    {

        return 1;
    }
    public void forbindBankKonto(int BrugerID, int BankKontoID)
    {

    }
}
