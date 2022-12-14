import java.sql.*;
import java.util.ArrayList;

public class DBSQL {
    private Connection connection;
    private Statement stmt;
    private Statement stmt1;

    DBSQL() {
        connection = null;
        stmt = null;
        try {
            String url = "jdbc:sqlite:C:\\Users\\peite\\Desktop\\skole projekter\\Semester projekt\\MobilePayProjekt\\1.semesterprojekt.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void test() {
    try {
        String sql = "INSERT INTO Person (navn, cpr, telefonNR, kode) VALUES('5','6','7','8')";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }    public void withdrawMoney(Bruger b, int beløb)
    {

    }
    public void depositMoney(Bruger b, int beløb)
    {

    }
    public void opretTransaktion(Transaktion t)
    {

    }
    public void opretPerson(Person p)
    {
        try {
            String sql = "INSERT INTO Person (navn, cpr, telefonNR, kode) VALUES('" +p.getNavn() + "','" +  p.getCpr() + "','" + p.getTelefonNR() + "','" + p.getKode() +"')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String pID = "null";

        try {
            String sql1 = "SELECT Person.personID FROM Person WHERE Person.cpr='" + p.getCpr() +"'";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql1);
            if(rs.next())
            {
                pID = rs.getString("personID");
            }
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            String sql2 = "INSERT INTO Bruger (personID) VALUES('" + pID +"')";

            Statement stmt = connection.createStatement();
            stmt.execute(sql2);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void opretVirksomhed(Virksomhed v)
    {
        try {
            String sql = "INSERT INTO Virksomhed (navn, cvr, virksomhedNR, kode) VALUES('" +v.getNavn() + "','" +  v.getCvr() + "','" + v.getVirksomhedsNR() + "','" + v.getKode() +"')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String vID = "null";

        try {
            String sql1 = "SELECT Virksomhed.virksomhedID FROM Virksomhed WHERE Virksomhed.cvr='" + v.getCvr() +"'";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql1);
            if(rs.next())
            {
                vID = rs.getString("virksomhedID");
            }
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            String sql2 = "INSERT INTO Bruger (virksomhedID) VALUES('" + vID +"')";

            Statement stmt = connection.createStatement();
            stmt.execute(sql2);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
