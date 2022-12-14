import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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
    }  public void withdrawMoney(Bruger b, int beløb)
    {

    }
    public void depositMoney(Bruger b, int beløb)
    {

    }
    public void opretTransaktion(Transaktion t)
    {
        try {
            String sql = "INSERT INTO Transaktion(afsenderID,modtagerID,beløb,dato,kommentar) VALUES('" +t.getAfsender().getBrugerID() + "','" + t.getModtager().getBrugerID() + "'," +
                    t.getAmount() + ",'" + "2022" +"','"+t.getKommentar()+"')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void opretAnmodning(Transaktion t)
    {
        try {
            String sql = "INSERT INTO Anmod(afsenderID,modtagerID,beløb,dato,kommentar) VALUES('" +t.getAfsender().getBrugerID() + "','" + t.getModtager().getBrugerID() + "'," +
                    t.getAmount() + ",'" + "2022" +"','"+t.getKommentar()+"')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
    public ArrayList<Transaktion> hentHistorikPerson(int personID)
    {
        int afsenderPersonID =0;
        int modtagerPersonID =0;
        int brugerID =0;
        ArrayList<Transaktion> historik = new ArrayList<Transaktion>();
        try {
            String sql1 = "SELECT Bruger.brugerID from Bruger WHERE Bruger.personID ="+personID;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql1);
            if(rs.next())
            {
                 brugerID=rs.getInt(1);
            }
            } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
            try {
            String sql2 = "SELECT * from Transaktion Where afsenderID ="+brugerID+"or modtagerID="+brugerID;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next())
            {
                //vi henter oplysninger på afsenderen
                afsenderPersonID =rs.getInt(1);
                String sql4 = "SELECT Person.name, Person.cpr,Person.telefonNR,Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID ="+afsenderPersonID;
                Statement stmt4 = connection.createStatement();
                ResultSet rs2 = stmt.executeQuery(sql4);
                Person afsender = new Person();
                afsender.setNavn(rs2.getString("navn"));
                afsender.setCpr(rs2.getString("cpr"));
                afsender.setTelefonNR(rs2.getString("telefonNR"));
                afsender.setKode(rs2.getString("kode"));
                //Vi henter oplysninger på modtageren
                modtagerPersonID=rs2.getInt(2);
                String sql3 = "SELECT Person.name, Person.cpr,Person.telefonNR,Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID ="+modtagerPersonID;
                Statement stmt3 = connection.createStatement();
                ResultSet rs1= stmt.executeQuery(sql2);
                Person modtager = new Person();
                afsender.setNavn(rs1.getString("navn"));
                afsender.setCpr(rs1.getString("cpr"));
                afsender.setTelefonNR(rs1.getString("telefonNR"));
                afsender.setKode(rs1.getString("kode"));
                //Transaktion oprettes
                Transaktion t1 = new Transaktion();
                t1.setAfsender(afsender);
                t1.setModtager(modtager);
                t1.setAmount(rs1.getDouble("beløb"));
                t1.setDato(new Date());
                t1.setKommentar(rs1.getString("kommentar"));

                historik.add(t1);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return historik;
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
