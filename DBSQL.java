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
            String url = "jdbc:sqlite:C:\\Users\\Kevin\\Documents\\GitHub\\MobilePayProjekt\\1.semesterprojekt.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void withdrawMoney(Bruger b, int beløb) {

    }

    public void depositMoney(Bruger b, int beløb) {

    }

    public void opretTransaktion(Transaktion t) {
        try {
            String sql = "INSERT INTO Transaktion(afsenderID,modtagerID,beløb,dato,kommentar) VALUES('" + t.getAfsender().getBrugerID() + "','" + t.getModtager().getBrugerID() + "'," +
                    t.getAmount() + ",'" + "2022" + "','" + t.getKommentar() + "')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opretAnmodning(Transaktion t) {
        try {
            String sql = "INSERT INTO Anmod(afsenderID,modtagerID,beløb,dato,kommentar) VALUES('" + t.getAfsender().getBrugerID() + "','" + t.getModtager().getBrugerID() + "'," +
                    t.getAmount() + ",'" + "2022" + "','" + t.getKommentar() + "')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opretPerson(Person p) {
        try {
            String sql = "INSERT INTO Person (navn, cpr, telefonNR, kode) VALUES('" + p.getNavn() + "','" + p.getCpr() + "','" + p.getTelefonNR() + "','" + p.getKode() + "')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String pID = "null";

        try {
            String sql1 = "SELECT Person.personID FROM Person WHERE Person.cpr='" + p.getCpr() + "'";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql1);
            if (rs.next()) {
                pID = rs.getString("personID");
            }
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            String sql2 = "INSERT INTO Bruger (personID) VALUES('" + pID + "')";

            Statement stmt = connection.createStatement();
            stmt.execute(sql2);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void opretVirksomhed(Virksomhed v) {
        try {
            String sql = "INSERT INTO Virksomhed (navn, cvr, virksomhedNR, kode) VALUES('" + v.getNavn() + "','" + v.getCvr() + "','" + v.getVirksomhedsNR() + "','" + v.getKode() + "')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String vID = "null";

        try {
            String sql1 = "SELECT Virksomhed.virksomhedID FROM Virksomhed WHERE Virksomhed.cvr='" + v.getCvr() + "'";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql1);
            if (rs.next()) {
                vID = rs.getString("virksomhedID");
            }
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            String sql2 = "INSERT INTO Bruger (virksomhedID) VALUES('" + vID + "')";

            Statement stmt = connection.createStatement();
            stmt.execute(sql2);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Transaktion> hentHistorik(int personID) {
        int afsenderPersonID = 0; // Holder personID/virksomhedID fra databatasen når denne er fundet
        int modtagerPersonID = 0; // Holder personID/virksomhedID fra databatasen når denne er fundet
        int brugerID = 0;
        ArrayList<Transaktion> historik = new ArrayList<Transaktion>(); //Der tilføjes transaktion objekter til denne og metoden returnere denne
        //Vi får en int som parameter, enten et telefonNR eller virksomhedNR fra den aktive bruger
        //Denne variable bruges til at finde brugerID fra bruger tabel (Skal bruges til at trække transaktioner ud af db)
        try {
            String sql1 = "SELECT Bruger.brugerID from Bruger WHERE Bruger.personID =" + personID;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql1);
            if (rs.next()) {
                brugerID = rs.getInt(1);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Når vi har fundet bruger ID trækker vi en liste af alle transaktioner hvor denne bruger er modtager eller afsender
        try {
            String sql2 = "SELECT Transaktion.afsenderID, Transaktion.modtagerID, Transaktion.beløb, Transaktion.dato, Transaktion.kommentar from Transaktion Where Transaktion.afsenderID ="+brugerID+ " OR Transaktion.modtagerID ="+brugerID;
            Statement stmt2 = connection.createStatement();
            ResultSet rs = stmt2.executeQuery(sql2);
            //Vi kører listen med transaktioner igennem
            while (rs.next()) {
                //vi henter oplysninger på afsenderen. Afsender kan kun være en person
                afsenderPersonID = rs.getInt("afsenderID");
                modtagerPersonID = rs.getInt("modtagerID");
                double amount = rs.getDouble("beløb");
                String dato = rs.getString("dato");
                String kommentar = rs.getString("kommentar");
                Person afsender = new Person();
                try {
                        String sql4 = "SELECT Person.navn, Person.cpr,Person.telefonNR,Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID =" + personID;
                    Statement stmt4 = connection.createStatement();
                    ResultSet rs2 = stmt4.executeQuery(sql4);
                    //oplysninger fra databases bruges til at oprette en person som kaldes afsender
                    afsender.setNavn(rs2.getString("navn"));
                    afsender.setCpr(rs2.getString("cpr"));
                    afsender.setTelefonNR(rs2.getString("telefonNR"));
                    afsender.setKode(rs2.getString("kode"));
                }
                 catch (SQLException e) {
                throw new RuntimeException(e);
            }
                //Vi henter oplysninger på modtageren
                try {
                    //Vi henter brugertabellen for at kunne sortere Personer og virksomheder.
                    String sql5 = "Select Bruger.personID, Bruger.virksomhedID from Bruger INNER JOIN Transaktion ON Transaktion.modtagerID = Bruger.brugerID WHERE Transaktion.modtagerID = "+modtagerPersonID;
                    Statement stmt5 = connection.createStatement();
                    ResultSet rs5 = stmt5.executeQuery(sql5);
                    // Vi opretter 2 int for at holde endten personID eller virksomhedID fra brugertabel
                    int bpID = 0;
                    int bvID = 0;
                    bpID = rs5.getInt("personID");
                    bvID = rs5.getInt("virksomhedID");
                    // virksomhedID og personID sættes til 0 og vi henter værdierne fra databasen. Hvis det vi har fundet er en virksomhed vil bvID være større end nul
                    if (bvID > 0) {
                        String sql3 = "SELECT Virksomhed.navn, Virksomhed.cvr, Virksomhed.virksomhedNR, Virksomhed.kode from Virksomhed inner join Bruger on Virksomhed.virksomhedID = Bruger.virksomhedID WHERE Virksomhed.virksomhedID =" + bvID;
                        Statement stmt3 = connection.createStatement();
                        ResultSet rs1 = stmt3.executeQuery(sql3);
                        //Der oprettes et virksomhed objekt
                        Virksomhed modtager = new Virksomhed();
                        modtager.setNavn(rs1.getString("navn"));
                        modtager.setCvr(rs1.getString("cvr"));
                        modtager.setVirksomhedsNR(rs1.getString("virksomhedNR"));
                        modtager.setKode(rs1.getString("kode"));
                        //der oprettes en transaktion med afsender og modtager bruger objekter og beløb dato og kommentar fra databasen
                        Transaktion t1 = new Transaktion();
                        t1.setAfsender(afsender);
                        t1.setModtager(modtager);
                        t1.setAmount(amount);
                        t1.setDato(new Date()); //TO-DO
                        t1.setKommentar(kommentar);
                        //transaktion tilføjes til listen
                        historik.add(t1);
                        stmt3.close();
                    }
                    if (bpID > 0) {
                        String sql3 = "SELECT Person.navn, Person.cpr,Person.telefonNR,Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID =" + bpID;
                        Statement stmt3 = connection.createStatement();
                        ResultSet rs1 = stmt3.executeQuery(sql3);
                        Person modtager = new Person();
                        modtager.setNavn(rs1.getString("navn"));
                        modtager.setCpr(rs1.getString("cpr"));
                        modtager.setTelefonNR(rs1.getString("telefonNR"));
                        modtager.setKode(rs1.getString("kode"));

                        //der oprettes en transaktion med afsender og modtager bruger objekter og beløb dato og kommentar fra databasen
                        Transaktion t1 = new Transaktion();
                        t1.setAfsender(afsender);
                        t1.setModtager(modtager);
                        t1.setAmount(amount);
                        t1.setDato(new Date()); //To-do
                        t1.setKommentar(kommentar);
                        //transaktion tilføjes til listen
                        historik.add(t1);
                        stmt3.close();
                    }
                    stmt5.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(historik.get(1).getAfsender().getNavn()+" "+historik.get(1).getModtager().getNavn() );
        return historik;

    }

    public ArrayList<Transaktion> hentAnmodninger(int brugerID) {

        return new ArrayList<Transaktion>();
    }

    public Bruger hentVirksomhed(String VirksomhedsNR) {
        return new Bruger();
    }

    public Bruger hentPerson(String telefonNR) {
        return new Bruger();
    }

    public int findBankKonto(int kontoNR) {

        return 1;
    }

    public void forbindBankKonto(int BrugerID, int BankKontoID) {

    }


}
