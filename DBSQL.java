import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DBSQL {
    private Connection connection;
    private Statement stmt;

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

    public void withdrawMoney(Bruger b, double amount) {
       double nyBalance = b.getB().getBalance() - amount;
        try {
        String sql = "UPDATE BankKonto SET balance =" + nyBalance + " WHERE kontoNR ="+b.getB().getKontoNR();
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void depositMoney(Bruger b, double amount) {
        double nyBalance = b.getB().getBalance() + amount;
        try {
            String sql = "UPDATE BankKonto SET balance =" + nyBalance + " WHERE kontoNR ="+b.getB().getKontoNR();
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opretTransaktion(Transaktion t) {
        int afsenderBrugerID = 0;
        try {
            String sql5 = "Select Bruger.personID, Bruger.virksomhedID from Bruger Where Bruger.personID =" + t.getAfsender().getBrugerID();
            Statement stmt5 = connection.createStatement();
            ResultSet rs5 = stmt5.executeQuery(sql5);
            // Vi opretter 2 int for at holde endten personID eller virksomhedID fra brugertabel
            int abpID = 0;
            int abvID = 0;
            abpID = rs5.getInt("personID");
            abvID = rs5.getInt("virksomhedID");
            if (abpID > 0) {
                try {
                    String sql20 = "Select Bruger.brugerID From Bruger where Bruger.personID=" + t.getAfsender().getBrugerID();
                    Statement stmt20 = connection.createStatement();
                    ResultSet rs20 = stmt20.executeQuery(sql20);
                    afsenderBrugerID = rs20.getInt(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (abvID > 0) {
                try {
                    String sql25 = "Select Bruger.brugerID From Bruger where Bruger.virksomhedID=" + t.getAfsender().getBrugerID();
                    Statement stmt25 = connection.createStatement();
                    ResultSet rs25 = stmt25.executeQuery(sql25);
                    afsenderBrugerID = rs25.getInt(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int mbpID = 0;
        int mbvID = 0;
        int modtagerBrugerID = 0;
        try {
            String sql6 = "Select Bruger.personID, Bruger.virksomhedID from Bruger Where Bruger."+t.getModtager().getClass().getName().toLowerCase()+"ID"+" =" + t.getModtager().getBrugerID();
            Statement stmt6 = connection.createStatement();
            ResultSet rs6 = stmt6.executeQuery(sql6);
            /* Vi opretter 2 int for at holde endten personID eller virksomhedID fra brugertabel */
            mbpID = rs6.getInt("personID");
            mbvID = rs6.getInt("virksomhedID");
            }   catch(SQLException throwables){
                throwables.printStackTrace();
            }
            if (mbpID > 0) {
                try {
                    String sqlp = "Select Bruger.brugerID From Bruger where Bruger.personID=" + t.getModtager().getBrugerID();
                    Statement stmtp = connection.createStatement();
                    ResultSet rsp = stmtp.executeQuery(sqlp);
                    modtagerBrugerID = rsp.getInt(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
                if (mbvID > 0) {
                    try {
                        String sqlv = "Select Bruger.brugerID From Bruger where Bruger.virksomhedID=" + t.getModtager().getBrugerID();
                        Statement stmtv = connection.createStatement();
                        ResultSet rsv = stmtv.executeQuery(sqlv);
                        modtagerBrugerID = rsv.getInt(1);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                        String sql = "INSERT INTO Transaktion(afsenderID,modtagerID,beløb,dato,kommentar) VALUES(" + afsenderBrugerID + "," + modtagerBrugerID + "," +
                                t.getAmount() + ",'" + "2022" + "','" + t.getKommentar() + "')";
                        try {
                            Statement stmt = connection.createStatement();
                            stmt.execute(sql);
                            stmt.close();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }

    public void opretAnmodningFraPerson(Transaktion t) {
        int afsenderBrugerID = 0;
        try {
            String sql5 = "Select Bruger.personID, Bruger.virksomhedID from Bruger Where Bruger.personID =" + t.getAfsender().getBrugerID();
            Statement stmt5 = connection.createStatement();
            ResultSet rs5 = stmt5.executeQuery(sql5);
            // Vi opretter 2 int for at holde endten personID eller virksomhedID fra brugertabel
            int abpID = 0;
            int abvID = 0;
            abpID = rs5.getInt("personID");
            abvID = rs5.getInt("virksomhedID");
            if (abpID > 0) {
                try {
                    String sql20 = "Select Bruger.brugerID From Bruger where Bruger.personID=" + t.getAfsender().getBrugerID();
                    Statement stmt20 = connection.createStatement();
                    ResultSet rs20 = stmt20.executeQuery(sql20);
                    afsenderBrugerID = rs20.getInt(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (abvID > 0) {
                try {
                    String sql25 = "Select Bruger.brugerID From Bruger where Bruger.virksomhedID=" + t.getAfsender().getBrugerID();
                    Statement stmt25 = connection.createStatement();
                    ResultSet rs25 = stmt25.executeQuery(sql25);
                    afsenderBrugerID = rs25.getInt(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int mbpID = 0;
        int mbvID = 0;
        int modtagerBrugerID = 0;
        try {
            String sql6 = "Select Bruger.personID, Bruger.virksomhedID from Bruger Where Bruger.personID =" + t.getModtager().getBrugerID();
            Statement stmt6 = connection.createStatement();
            ResultSet rs6 = stmt6.executeQuery(sql6);
            /* Vi opretter 2 int for at holde endten personID eller virksomhedID fra brugertabel */
            mbpID = rs6.getInt("personID");
            mbvID = rs6.getInt("virksomhedID");
        }   catch(SQLException throwables){
            throwables.printStackTrace();
        }

        if (mbpID > 0) {
            try {
                String sqlp = "Select Bruger.brugerID From Bruger where Bruger.personID=" + t.getModtager().getBrugerID();
                Statement stmtp = connection.createStatement();
                ResultSet rsp = stmtp.executeQuery(sqlp);
                modtagerBrugerID = rsp.getInt(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (mbvID > 0) {
                try {
                    String sqlv = "Select Bruger.brugerID From Bruger where Bruger.virksomhedID=" + t.getModtager().getBrugerID();
                    Statement stmtv = connection.createStatement();
                    ResultSet rsv = stmtv.executeQuery(sqlv);
                    modtagerBrugerID = rsv.getInt(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            String sql = "INSERT INTO Anmod(afsenderID,modtagerID,beløb,dato,kommentar) VALUES(" + afsenderBrugerID + "," + modtagerBrugerID + "," +
                    t.getAmount() + ",'" + "2022" + "','" + t.getKommentar() + "')";
            try {
                Statement stmt = connection.createStatement();
                stmt.execute(sql);
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void opretAnmodningFraVirksomhed(Transaktion t){
        int afsenderBrugerID = 0;
        try {
            String sql5 = "Select Bruger.personID, Bruger.virksomhedID from Bruger Where Bruger.virksomhedID =" + t.getAfsender().getBrugerID();
            Statement stmt5 = connection.createStatement();
            ResultSet rs5 = stmt5.executeQuery(sql5);
            // Vi opretter 2 int for at holde endten personID eller virksomhedID fra brugertabel
            int abpID = 0;
            int abvID = 0;
            abpID = rs5.getInt("personID");
            abvID = rs5.getInt("virksomhedID");
            if (abpID > 0) {
                try {
                    String sql20 = "Select Bruger.brugerID From Bruger where Bruger.virksomhedID=" + t.getAfsender().getBrugerID();
                    Statement stmt20 = connection.createStatement();
                    ResultSet rs20 = stmt20.executeQuery(sql20);
                    afsenderBrugerID = rs20.getInt(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (abvID > 0) {
                try {
                    String sql25 = "Select Bruger.brugerID From Bruger where Bruger.virksomhedID=" + t.getAfsender().getBrugerID();
                    Statement stmt25 = connection.createStatement();
                    ResultSet rs25 = stmt25.executeQuery(sql25);
                    afsenderBrugerID = rs25.getInt(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int mbpID = 0;
        int mbvID = 0;
        int modtagerBrugerID = 0;
        try {
            String sql6 = "Select Bruger.personID, Bruger.virksomhedID from Bruger Where Bruger.personID =" + t.getModtager().getBrugerID();
            Statement stmt6 = connection.createStatement();
            ResultSet rs6 = stmt6.executeQuery(sql6);
            /* Vi opretter 2 int for at holde endten personID eller virksomhedID fra brugertabel */
            mbpID = rs6.getInt("personID");
            mbvID = rs6.getInt("virksomhedID");
        }   catch(SQLException throwables){
            throwables.printStackTrace();
        }

        if (mbpID > 0) {
            try {
                String sqlp = "Select Bruger.brugerID From Bruger where Bruger.personID=" + t.getModtager().getBrugerID();
                Statement stmtp = connection.createStatement();
                ResultSet rsp = stmtp.executeQuery(sqlp);
                modtagerBrugerID = rsp.getInt(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (mbvID > 0) {
                try {
                    String sqlv = "Select Bruger.brugerID From Bruger where Bruger.virksomhedID=" + t.getModtager().getBrugerID();
                    Statement stmtv = connection.createStatement();
                    ResultSet rsv = stmtv.executeQuery(sqlv);
                    modtagerBrugerID = rsv.getInt(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            String sql = "INSERT INTO Anmod(afsenderID,modtagerID,beløb,dato,kommentar) VALUES(" + afsenderBrugerID + "," + modtagerBrugerID + "," +
                    t.getAmount() + ",'" + "2022" + "','" + t.getKommentar() + "')";
            try {
                Statement stmt = connection.createStatement();
                stmt.execute(sql);
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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

    public ArrayList<Transaktion> hentHistorik(String NR)//Vi får en int som parameter, enten et telefonNR eller virksomhedNR fra den aktive bruger
    {
        int personID=0; //Vi finder ID på person/virksomhed i databasen og gemmer i denne værdi
        int length= String.valueOf(NR).length(); //Vi finder længden på String NR, dette bruges til at tjekke om det er 5 cifret(virksomhed) eller Person
        int afsenderID =0;
        int modtagerID =0;
        int brugerID = 0; //brugerID er det Bruger.BrugerID i databasen som holder styr på om det er en person eller virksomhed
        //Vi laver listerne af værdier fra databasen.
        ArrayList<Integer> afsenderIDliste = new ArrayList<>();
        ArrayList<Integer> modtagerIDliste = new ArrayList<>();
        ArrayList<Double> amountliste = new ArrayList<>();
        ArrayList<String> datoliste = new ArrayList<>();
        ArrayList<String> kommentarliste = new ArrayList<>();
        ArrayList<Transaktion> historik = new ArrayList<Transaktion>(); //Der tilføjes transaktion objekter til denne og denne returneres
        if(length>5) { //hvis lenght er længere end 5 er det en Person, ikke virksomhed, så vi finder ID i person tabel
            try {
                String sql1 = "SELECT Person.personID from Person WHERE Person.telefonNR =" + NR;
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql1);
                if (rs.next()) {
                    personID = rs.getInt(1);
                }
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //Vi finder også Bruger.brugerID ud fra personID til senere brug
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
        }
        else{ //Hvis lenght ikke er længere end 5 så er det en virksomhed og vi finder personID i virksomhed tabellen
            try {
                String sql1 = "SELECT Virksomhed.virksomhedID from Virksomhed WHERE Virksomhed.virksomhedNR =" + NR;
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql1);
                if (rs.next()) {
                    personID = rs.getInt(1);
                }
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try { //og finder Bruger.brugerID hvis det er en virksomhed
                String sql1 = "SELECT Bruger.brugerID from Bruger WHERE Bruger.virksomhedID =" + personID;
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql1);
                if (rs.next()) {
                    brugerID = rs.getInt(1);
                }
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //Når vi har fundet bruger ID trækker vi en liste af alle transaktioner hvor den Bruger.brugerID vi har fundet er endten afsender eller modtagerr
        try {
            String sql2 = "SELECT Transaktion.afsenderID, Transaktion.modtagerID, Transaktion.beløb, Transaktion.dato, Transaktion.kommentar from Transaktion Where Transaktion.afsenderID =" + brugerID + " OR Transaktion.modtagerID =" + brugerID;
            Statement stmt2 = connection.createStatement();
            ResultSet rsTrans = stmt2.executeQuery(sql2);
            //Vi kører listen med transaktioner igennem og gemmer værdierne på de pladser der hører sammen.
            while (rsTrans.next()) {
                //vi henter oplysninger på afsenderen. Afsender kan kun være en person
                afsenderIDliste.add(rsTrans.getInt("afsenderID"));
                modtagerIDliste.add(rsTrans.getInt("modtagerID"));
                amountliste.add(rsTrans.getDouble("beløb"));
                datoliste.add(rsTrans.getString("dato"));
                kommentarliste.add(rsTrans.getString("kommentar"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Nu skal vi gå listen af transaktioner igennem og finde afsender og modtager. Da det både kan være personer og virksomheder, laves der objekter til disse.
        for (int i = 0; i < afsenderIDliste.size(); i++) {
            Transaktion t1 = new Transaktion(); //når vi har fundet de korrekte brugere sættes de ind på denne transaktion og den skrives til historik
            Person pafsender = new Person();
            Person pmodtager = new Person();
            Virksomhed vafsender = new Virksomhed();
            Virksomhed vmodtager = new Virksomhed();
            int bpID = 0;
            int bvID = 0;

            try{    //Vi tager person og virksomhedsID ud for den bruger vi er nået til og ligger dem i bpID (Bruger personID) eller bvID (Bruger virksomhedID)
                String sql1 = "SELECT Bruger.personID,Bruger.virksomhedID From Bruger where Bruger.BrugerID ="+afsenderIDliste.get(i);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql1);
               while (rs.next()) {
                   bpID = rs.getInt("personID");
                   bvID= rs.getInt("virksomhedID");
                }
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }//vi ved at endten er en bruger en person eller virksomhed. dvs. hvis personID er mere end nul er brugeren en person, så vi henter personID
            if (bpID >0) {
                try {
                    String sql1 = "SELECT Bruger.personID from Bruger WHERE Bruger.brugerID =" + afsenderIDliste.get(i);
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql1);
                    if (rs.next()) {
                        afsenderID = rs.getInt(1);
                    }
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try { //PersonID bruges til at finde oplysningerne på denne person i databasen
                    String sql4 = "SELECT Person.personID, Person.navn, Person.cpr, Person.telefonNR, Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID =" + afsenderID;
                    Statement stmt4 = connection.createStatement();
                    ResultSet rs4 = stmt4.executeQuery(sql4);
                    //oplysninger fra databases bruges til at oprette en person som kaldes afsender
                    pafsender.setBrugerID(rs4.getInt("personID"));
                    pafsender.setNavn(rs4.getString("navn"));
                    pafsender.setCpr(rs4.getString("cpr"));
                    pafsender.setTelefonNR(rs4.getString("telefonNR"));
                    pafsender.setKode(rs4.getString("kode"));
                    //Afsender tilføjes til transaktionen
                    t1.setAfsender(pafsender);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (bvID > 0)
             { //hvis virksomhedID er mere end 0 så tager vi data fra virksomhed tabellen
                 try {
                     String sql4 = "SELECT Virksomhed.virksomhedID, Virksomhed.navn, Virksomhed.cpr, Virksomhed.virksomhedNR, Virksomhed.kode from Virksomhed inner join Bruger on Virksomhed.virksomhedID = Bruger.virksomhedID WHERE Virksomhed.virksomhedID =" + afsenderID;
                     Statement stmt4 = connection.createStatement();
                     ResultSet rs4 = stmt4.executeQuery(sql4);
                     //oplysninger fra databases bruges til at oprette en person som kaldes afsender
                     pafsender.setBrugerID(rs4.getInt("personID"));
                     pafsender.setNavn(rs4.getString("navn"));
                     pafsender.setCpr(rs4.getString("cpr"));
                     pafsender.setTelefonNR(rs4.getString("telefonNR"));
                     pafsender.setKode(rs4.getString("kode"));
                     //afsenderen tilføjes til transaktionen
                     t1.setAfsender(pafsender);
                 } catch (SQLException e) {
                     throw new RuntimeException(e);
                 }
             }
            try{   //vi skal nu finde modtager. VI tager personID og virksomhed ID fra databasen ud fra modtagers ID
            String sql5 = "Select Bruger.personID, Bruger.virksomhedID from Bruger INNER JOIN Transaktion ON Transaktion.modtagerID = Bruger.brugerID WHERE Transaktion.modtagerID = "+modtagerIDliste.get(i);
            Statement stmt5 = connection.createStatement();
            ResultSet rs5 = stmt5.executeQuery(sql5);
            bpID =0;
            bvID =0;
            // Vi opretter 2 int for at holde endten personID eller virksomhedID fra brugertabel
            bpID = rs5.getInt("personID");
            bvID = rs5.getInt("virksomhedID");
            }catch (SQLException throwables) {
                throwables.printStackTrace();}
            //Hvis modtager er en virksomhed tjekkes Bruger tabel for at få virksomhedID på denne bruger. Dette gemmes i modtagerID
            if (bvID > 0) {
                try {
                    String sql1 = "SELECT Bruger.virksomhedID from Bruger WHERE Bruger.brugerID =" + modtagerIDliste.get(i);
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql1);
                    if (rs.next()) {
                        modtagerID = rs.getInt(1);
                    }
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try { //vi bruger modtager ID til at få virksomheds oplysnigerne
                    String sql15 = "SELECT Virksomhed.virksomhedID,Virksomhed.navn, Virksomhed.cvr, Virksomhed.virksomhedNR, Virksomhed.kode from Virksomhed inner join Bruger on Virksomhed.virksomhedID = Bruger.virksomhedID WHERE Virksomhed.virksomhedID =" + modtagerID;
                    Statement stmt15 = connection.createStatement();
                    ResultSet rs15 = stmt15.executeQuery(sql15);
                    //virksomheds oplysninger gemmes i vmodtager
                    vmodtager.setBrugerID(rs15.getInt("virksomhedID"));
                    vmodtager.setNavn(rs15.getString("navn"));
                    vmodtager.setCvr(rs15.getString("cvr"));
                    vmodtager.setVirksomhedsNR(rs15.getString("virksomhedNR"));
                    vmodtager.setKode(rs15.getString("kode"));
                    //vmodtager sættes som modtager til transaktionen
                    t1.setModtager(vmodtager);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
                if (bpID > 0) { //Hvis modtager er en eprson bruger vi personID til at få brugerID'et
                    try {
                        String sql1 = "SELECT Bruger.personID from Bruger WHERE Bruger.brugerID ="+modtagerIDliste.get(i);
                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery(sql1);
                        if (rs.next()) {
                            modtagerID = rs.getInt(1);
                        }
                        stmt.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try { //vi finder oplysningerne i person tabel
                        String sql3 = "SELECT Person.personID, Person.navn, Person.cpr,Person.telefonNR,Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID =" + modtagerID;
                        Statement stmt3 = connection.createStatement();
                        ResultSet rs3 = stmt3.executeQuery(sql3);
                        //vi oplysningerne ind på pmodtager objektet
                        pmodtager.setBrugerID(rs3.getInt("personID"));
                        pmodtager.setNavn(rs3.getString("navn"));
                        pmodtager.setCpr(rs3.getString("cpr"));
                        pmodtager.setTelefonNR(rs3.getString("telefonNR"));
                        pmodtager.setKode(rs3.getString("kode"));
                        //pmodtager tilføjes som modtager til vores transaktion
                        t1.setModtager(pmodtager);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);}
                    }
                    //dato beløb og kommentar sættes på transaktionen og denne tilføjes til historikken (ArrayList<Transaktion> historik)
                    t1.setDato(new Date());
                    t1.setAmount(amountliste.get(i));
                    t1.setKommentar(kommentarliste.get(i));
                    historik.add(t1);
                }
        return historik;
    }

    public ArrayList<Transaktion> hentAnmodninger(String NR) {

        int personID=0;
        int length= String.valueOf(NR).length();
        int afsenderID =0;
        int modtagerID =0;
        int brugerID = 0; //brugerID er det Bruger.BrugerID i databasen som holder styr på om det er en person eller virksomhed
        //Vi laver listerne af værdier fra databasen.
        ArrayList<Integer> afsenderIDliste = new ArrayList<>();
        ArrayList<Integer> modtagerIDliste = new ArrayList<>();
        ArrayList<Double> amountliste = new ArrayList<>();
        ArrayList<String> datoliste = new ArrayList<>();
        ArrayList<String> kommentarliste = new ArrayList<>();
        ArrayList<Transaktion> historik = new ArrayList<Transaktion>(); //Der tilføjes transaktion objekter til denne og metoden returnere denne
        if(length>5) {
            try {
                String sql1 = "SELECT Person.personID from Person WHERE Person.telefonNR =" + NR;
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql1);
                if (rs.next()) {
                    personID = rs.getInt(1);
                }
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
        }
        else {
            try {
                String sql1 = "SELECT Virksomhed.virksomhedID from Virksomhed WHERE Virksomhed.virksomhedNR =" + NR;
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql1);
                if (rs.next()) {
                    personID = rs.getInt(1);
                }
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
        }
        //Når vi har fundet bruger ID trækker vi en liste af alle transaktioner hvor denne bruger er modtager eller afsender
        try {
            String sql2 = "SELECT Anmod.afsenderID, Anmod.modtagerID, Anmod.beløb, Anmod.dato, Anmod.kommentar from Anmod Where Anmod.afsenderID =" + brugerID + " OR Anmod.modtagerID =" + brugerID;
            Statement stmt2 = connection.createStatement();
            ResultSet rsTrans = stmt2.executeQuery(sql2);
            //Vi kører listen med transaktioner igennem
            while (rsTrans.next()) {
                //vi henter oplysninger på afsenderen. Afsender kan kun være en person
                afsenderIDliste.add(rsTrans.getInt("afsenderID"));
                modtagerIDliste.add(rsTrans.getInt("modtagerID"));
                amountliste.add(rsTrans.getDouble("beløb"));
                datoliste.add(rsTrans.getString("dato"));
                kommentarliste.add(rsTrans.getString("kommentar"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //vi går listen af anmodninger igennem
        for (int i = 0; i < afsenderIDliste.size(); i++) {
            Transaktion t1 = new Transaktion();
            Person pafsender = new Person();
            Person pmodtager = new Person();
            Virksomhed vafsender = new Virksomhed();
            Virksomhed vmodtager = new Virksomhed();
            int bpID = 0;
            int bvID = 0;
            try {
                String sql1 = "SELECT Bruger.personID from Bruger WHERE Bruger.brugerID =" + afsenderIDliste.get(i);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql1);
                if (rs.next()) {
                    afsenderID = rs.getInt(1);
                }
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                String sql4 = "SELECT Person.personID, Person.navn, Person.cpr, Person.telefonNR, Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID =" + afsenderID;
                Statement stmt4 = connection.createStatement();
                ResultSet rs4 = stmt4.executeQuery(sql4);
                //oplysninger fra databases bruges til at oprette en person som kaldes afsender
                pafsender.setBrugerID(rs4.getInt("personID"));
                pafsender.setNavn(rs4.getString("navn"));
                pafsender.setCpr(rs4.getString("cpr"));
                pafsender.setTelefonNR(rs4.getString("telefonNR"));
                pafsender.setKode(rs4.getString("kode"));
                t1.setAfsender(pafsender);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try{
                String sql5 = "Select Bruger.personID, Bruger.virksomhedID from Bruger INNER JOIN Anmod ON Anmod.modtagerID = Bruger.brugerID WHERE Anmod.modtagerID = "+modtagerIDliste.get(i);
                Statement stmt5 = connection.createStatement();
                ResultSet rs5 = stmt5.executeQuery(sql5);
                // Vi opretter 2 int for at holde endten personID eller virksomhedID fra brugertabel
                bpID = rs5.getInt("personID");
                bvID = rs5.getInt("virksomhedID");
            }catch (SQLException throwables) {
                throwables.printStackTrace();}
            //Hvis modtager er en virksomhed tjekkes virksomhed-tabellen og en virksomheds objekt oprettes og sættes som modtager
            if (bvID > 0) {
                try {
                    String sql1 = "SELECT Bruger.virksomhedID from Bruger WHERE Bruger.virksomhedID =" + modtagerIDliste.get(i);
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql1);
                    if (rs.next()) {
                        modtagerID = rs.getInt(1);
                    }
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    String sql15 = "SELECT Virksomhed.virksomhedID,Virksomhed.navn, Virksomhed.cvr, Virksomhed.virksomhedNR, Virksomhed.kode from Virksomhed inner join Bruger on Virksomhed.virksomhedID = Bruger.virksomhedID WHERE Virksomhed.virksomhedID =" + modtagerID;
                    Statement stmt15 = connection.createStatement();
                    ResultSet rs15 = stmt15.executeQuery(sql15);
                    vmodtager.setBrugerID(rs15.getInt("virksomhedID"));
                    vmodtager.setNavn(rs15.getString("navn"));
                    vmodtager.setCvr(rs15.getString("cvr"));
                    vmodtager.setVirksomhedsNR(rs15.getString("virksomhedNR"));
                    vmodtager.setKode(rs15.getString("kode"));
                    t1.setModtager(vmodtager);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (bpID > 0) {
                try {

                    String sql1 = "SELECT Bruger.personID from Bruger WHERE Bruger.brugerID ="+modtagerIDliste.get(i);
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql1);
                    if (rs.next()) {
                        modtagerID = rs.getInt(1);
                    }
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    String sql3 = "SELECT Person.personID, Person.navn, Person.cpr,Person.telefonNR,Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID =" + modtagerID;
                    Statement stmt3 = connection.createStatement();
                    ResultSet rs3 = stmt3.executeQuery(sql3);
                    Person modtager = new Person();
                    pmodtager.setBrugerID(rs3.getInt("personID"));
                    pmodtager.setNavn(rs3.getString("navn"));
                    pmodtager.setCpr(rs3.getString("cpr"));
                    pmodtager.setTelefonNR(rs3.getString("telefonNR"));
                    pmodtager.setKode(rs3.getString("kode"));
                    t1.setModtager(pmodtager);
                } catch (SQLException e) {
                    throw new RuntimeException(e);}
            }
            t1.setDato(new Date());
            t1.setAmount(amountliste.get(i));
            t1.setKommentar(kommentarliste.get(i));
            historik.add(t1);
        }
        return historik;
    }

    public Virksomhed hentVirksomhed(String virksomhedsNR)
    {
        Virksomhed dataVirksomhed = new Virksomhed();
        BankKonto databankKonto = new BankKonto();
        int bankNR = 0;
        try {

            String sql = "SELECT Virksomhed.virksomhedID,Virksomhed.navn, Virksomhed.cvr, Virksomhed.virksomhedNR, Virksomhed.bankKontoID, Virksomhed.kode FROM Virksomhed WHERE virksomhedNR='" + virksomhedsNR + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                dataVirksomhed.setBrugerID(rs.getInt("virksomhedID"));
                dataVirksomhed.setNavn(rs.getString("navn"));
                dataVirksomhed.setCvr(rs.getString("cvr"));
                dataVirksomhed.setVirksomhedsNR(rs.getString("virksomhedNR"));
                bankNR = rs.getInt("bankKontoID");
                dataVirksomhed.setKode(rs.getString("kode"));
            }
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            String sql1 = "SELECT * FROM BankKonto WHERE bankKontoID=" + bankNR;
            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            while(rs1.next())
            {
                databankKonto.setBankKontoID(rs1.getInt("bankKontoID"));
                databankKonto.setBalance(rs1.getDouble("balance"));
                databankKonto.setKontoNR(rs1.getString("kontoNR"));
            }
            stmt1.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dataVirksomhed.setB(databankKonto);

        return dataVirksomhed;
    }
    public Person hentPerson(String telefonNR)
    {
        Person dataPerson = new Person();
        BankKonto databankKonto = new BankKonto();
        int bankNR = 0;
        try {
            String sql = "SELECT Person.personID, Person.navn, Person.cpr, Person.telefonNR, Person.bankKontoID, Person.kode FROM Person WHERE telefonNR='" + telefonNR + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                dataPerson.setBrugerID(rs.getInt("personID"));
                dataPerson.setNavn(rs.getString("navn"));
                dataPerson.setCpr(rs.getString("cpr"));
                dataPerson.setTelefonNR(rs.getString("telefonNR"));
                bankNR = rs.getInt("bankKontoID");
                dataPerson.setKode(rs.getString("kode"));
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            String sql1 = "SELECT * FROM BankKonto WHERE bankKontoID=" + bankNR;
            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            while(rs1.next())
            {
                databankKonto.setBankKontoID(rs1.getInt("bankKontoID"));
                databankKonto.setBalance(rs1.getDouble("balance"));
                databankKonto.setKontoNR(rs1.getString("kontoNR"));
            }
            stmt1.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dataPerson.setB(databankKonto);

        return dataPerson;
    }
    public BankKonto findBankKonto(String kontoNR)
    {
        BankKonto dataBankKonto = new BankKonto();
        try {
            String sql = "SELECT * FROM BankKonto WHERE kontoNR='"  +kontoNR + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                dataBankKonto.setBankKontoID(rs.getInt("bankKontoID"));
                dataBankKonto.setBalance(rs.getDouble("balance"));
                dataBankKonto.setKontoNR(rs.getString("kontoNR"));
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataBankKonto;
    }

    public void forbindBankKontoPerson(int BrugerID, String kontoNR)
    {
        int kontoIDny=0;
        try {
            String sql = "SELECT BankKonto.bankKontoID FROM BankKonto WHERE kontoNR=" + kontoNR;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                kontoIDny = rs.getInt("bankKontoID");
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        try {
            String sql1 = "UPDATE Person SET bankKontoID =" + kontoIDny + " WHERE personID=" + BrugerID;
            Statement stmt1 = connection.createStatement();
            stmt1.execute(sql1);
            stmt1.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void forbindBankKontoVirksomhed(int BrugerID, String kontoNR)
    {
        int kontoIDny=0;
        try {
            String sql = "SELECT BankKonto.bankKontoID FROM BankKonto WHERE kontoNR=" + kontoNR;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                kontoIDny = rs.getInt("bankKontoID");
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        try {
            String sql1 = "UPDATE Virksomhed SET bankKontoID =" + kontoIDny + " WHERE virksomhedID=" + BrugerID;
            Statement stmt1 = connection.createStatement();
            stmt1.execute(sql1);
            stmt1.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void sletAnmodning(Transaktion t)
    {
        try {
            String sql = "DELETE FROM Anmod WHERE Anmod.anmodID=" + t.getTransaktionID();
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
