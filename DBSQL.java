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
            String url = "jdbc:sqlite:C:\\Users\\Arbejde\\Desktop\\1.semesterprojektTEST.db";
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
            ResultSet rsTrans = stmt2.executeQuery(sql2);
            //Vi kører listen med transaktioner igennem
            while (rsTrans.next()) {
                //vi henter oplysninger på afsenderen. Afsender kan kun være en person
                afsenderPersonID = rsTrans.getInt("afsenderID");
                modtagerPersonID = rsTrans.getInt("modtagerID");;
                double amount = rsTrans.getDouble("beløb");
                String dato = rsTrans.getString("dato");
                String kommentar = rsTrans.getString("kommentar");
                Person afsender = new Person();

                try {
                    String sql4 = "SELECT Person.navn, Person.cpr, Person.telefonNR, Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID =" + afsenderPersonID;
                    Statement stmt4 = connection.createStatement();
                    ResultSet rs4 = stmt4.executeQuery(sql4);
                    //oplysninger fra databases bruges til at oprette en person som kaldes afsender
                    afsender.setNavn(rs4.getString("navn"));
                    afsender.setCpr(rs4.getString("cpr"));
                    afsender.setTelefonNR(rs4.getString("telefonNR"));
                    afsender.setKode(rs4.getString("kode"));
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
                        String sql15 = "SELECT Virksomhed.navn, Virksomhed.cvr, Virksomhed.virksomhedNR, Virksomhed.kode from Virksomhed inner join Bruger on Virksomhed.virksomhedID = Bruger.virksomhedID WHERE Virksomhed.virksomhedID =" + bvID;
                        Statement stmt15 = connection.createStatement();
                        ResultSet rs15 = stmt15.executeQuery(sql15);
                        //Der oprettes et virksomhed objekt
                        Virksomhed modtager = new Virksomhed();
                        modtager.setNavn(rs15.getString("navn"));
                        modtager.setCvr(rs15.getString("cvr"));
                        modtager.setVirksomhedsNR(rs15.getString("virksomhedNR"));
                        modtager.setKode(rs15.getString("kode"));
                        //der oprettes en transaktion med afsender og modtager bruger objekter og beløb dato og kommentar fra databasen
                        Transaktion t1 = new Transaktion();
                        t1.setAfsender(afsender);
                        t1.setModtager(modtager);
                        t1.setAmount(amount);
                        t1.setDato(new Date()); //TO-DO
                        t1.setKommentar(kommentar);
                        //transaktion tilføjes til listen
                        historik.add(t1);
                    }
                    if (bpID > 0) {
                        String sql3 = "SELECT Person.navn, Person.cpr,Person.telefonNR,Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID =" + bpID;
                        Statement stmt3 = connection.createStatement();
                        ResultSet rs3 = stmt3.executeQuery(sql3);
                        Person modtager = new Person();
                        modtager.setNavn(rs3.getString("navn"));
                        modtager.setCpr(rs3.getString("cpr"));
                        modtager.setTelefonNR(rs3.getString("telefonNR"));
                        modtager.setKode(rs3.getString("kode"));

                        //der oprettes en transaktion med afsender og modtager bruger objekter og beløb dato og kommentar fra databasen
                        Transaktion t1 = new Transaktion();
                        t1.setAfsender(afsender);
                        t1.setModtager(modtager);
                        t1.setAmount(amount);
                        t1.setDato(new Date()); //To-do
                        t1.setKommentar(kommentar);
                        //transaktion tilføjes til listen
                        historik.add(t1);
                    }

                    } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historik;

    }

    public ArrayList<Transaktion> hentAnmodninger(int personID) {

        int transaktionID = 0;
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
            String sql2 = "SELECT Anmod.anmodID, Anmod.afsenderID, Anmod.modtagerID, Anmod.beløb, Anmod.dato, Anmod.kommentar from Anmod Where Anmod.modtagerID ="+brugerID;
            Statement stmt2 = connection.createStatement();
            ResultSet rsTrans = stmt2.executeQuery(sql2);
            //Vi kører listen med transaktioner igennem
            while (rsTrans.next()) {
                //vi henter oplysninger på afsenderen. Afsender kan kun være en person
                transaktionID = rsTrans.getInt("AnmodID");
                afsenderPersonID = rsTrans.getInt("afsenderID");
                modtagerPersonID = rsTrans.getInt("modtagerID");;
                double amount = rsTrans.getDouble("beløb");
                String dato = rsTrans.getString("dato");
                String kommentar = rsTrans.getString("kommentar");

                Person afsender = new Person();
                try {
                    String sql4 = "SELECT Person.navn, Person.cpr, Person.telefonNR, Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Bruger.brugerID =" + afsenderPersonID;
                    Statement stmt4 = connection.createStatement();
                    ResultSet rs4 = stmt4.executeQuery(sql4);
                    //oplysninger fra databases bruges til at oprette en person som kaldes afsender
                    afsender.setNavn(rs4.getString("navn"));
                    afsender.setCpr(rs4.getString("cpr"));
                    afsender.setTelefonNR(rs4.getString("telefonNR"));
                    afsender.setKode(rs4.getString("kode"));
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //Vi henter oplysninger på modtageren
                try {
                    //Vi henter brugertabellen for at kunne sortere Personer og virksomheder.
                    String sql5 = "Select Bruger.personID, Bruger.virksomhedID from Bruger INNER JOIN Anmod ON Anmod.modtagerID = Bruger.brugerID WHERE Anmod.modtagerID = "+modtagerPersonID;
                    Statement stmt5 = connection.createStatement();
                    ResultSet rs5 = stmt5.executeQuery(sql5);
                    // Vi opretter 2 int for at holde endten personID eller virksomhedID fra brugertabel
                    int bpID = 0;
                    int bvID = 0;
                    bpID = rs5.getInt("personID");
                    bvID = rs5.getInt("virksomhedID");
                    // virksomhedID og personID sættes til 0 og vi henter værdierne fra databasen. Hvis det vi har fundet er en virksomhed vil bvID være større end nul
                    if (bvID > 0) {
                        String sql15 = "SELECT Virksomhed.navn, Virksomhed.cvr, Virksomhed.virksomhedNR, Virksomhed.kode from Virksomhed inner join Bruger on Virksomhed.virksomhedID = Bruger.virksomhedID WHERE Virksomhed.virksomhedID =" + bvID;
                        Statement stmt15 = connection.createStatement();
                        ResultSet rs15 = stmt15.executeQuery(sql15);
                        //Der oprettes et virksomhed objekt
                        Virksomhed modtager = new Virksomhed();
                        modtager.setNavn(rs15.getString("navn"));
                        modtager.setCvr(rs15.getString("cvr"));
                        modtager.setVirksomhedsNR(rs15.getString("virksomhedNR"));
                        modtager.setKode(rs15.getString("kode"));
                        //der oprettes en transaktion med afsender og modtager bruger objekter og beløb dato og kommentar fra databasen
                        Transaktion t1 = new Transaktion();
                        t1.setTransaktionID(transaktionID);
                        t1.setAfsender(afsender);
                        t1.setModtager(modtager);
                        t1.setAmount(amount);
                        t1.setDato(new Date()); //TO-DO
                        t1.setKommentar(kommentar);
                        //transaktion tilføjes til listen
                        historik.add(t1);
                    }
                    if (bpID > 0) {
                        String sql3 = "SELECT Person.navn, Person.cpr,Person.telefonNR,Person.kode from Person inner join Bruger on Person.personID = Bruger.personID WHERE Person.personID =" + bpID;
                        Statement stmt3 = connection.createStatement();
                        ResultSet rs3 = stmt3.executeQuery(sql3);
                        Person modtager = new Person();
                        modtager.setNavn(rs3.getString("navn"));
                        modtager.setCpr(rs3.getString("cpr"));
                        modtager.setTelefonNR(rs3.getString("telefonNR"));
                        modtager.setKode(rs3.getString("kode"));

                        //der oprettes en transaktion med afsender og modtager bruger objekter og beløb dato og kommentar fra databasen
                        Transaktion t1 = new Transaktion();
                        t1.setTransaktionID(transaktionID);
                        t1.setAfsender(afsender);
                        t1.setModtager(modtager);
                        t1.setAmount(amount);
                        t1.setDato(new Date()); //To-do
                        t1.setKommentar(kommentar);
                        //transaktion tilføjes til listen
                        historik.add(t1);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
