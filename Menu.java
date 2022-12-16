import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    private Bruger aktivBruger;
    private DBSQL dbSql = new DBSQL();


    //Constructor

    //Metoder
    public void godkendTransaktion(Transaktion t)
    {
        getDBSql().opretTransaktion(t);
        getDBSql().withdrawMoney(t.getAfsender(),t.getAmount());
        getDBSql().depositMoney(t.getModtager(),t.getAmount());
    }

    public void opretBruger()
    {
        System.out.println("Vil du oprette en privatbruger tryk 1");
        System.out.println("Vil du oprette en virksomhedsbruger tryk 2");
        Scanner input = new Scanner(System.in);
        int brugerInput = input.nextInt();
        if (brugerInput == 1){
            Person nyPerson = new Person();
            System.out.println("Indtast navn");
            nyPerson.setNavn(input.next());
            System.out.println("Indtast den ønskede kode");
            nyPerson.setKode(input.next());
            System.out.println("Indtast cpr nummer: ");
            nyPerson.setCpr(input.next());
            System.out.println("Indtast telefonnummer: ");
            nyPerson.setTelefonNR(input.next());
            dbSql.opretPerson(nyPerson);
            System.out.println("Du er nu oprettet!");

        } else if (brugerInput == 2)
        {
            Virksomhed nyVirksomhed = new Virksomhed();
            System.out.println("Indtast navn");
            nyVirksomhed.setNavn(input.next());
            System.out.println("Indsæt dit unikke 5 cifret nummer");// Virksomheden skal selv finde på et nummer
            nyVirksomhed.setVirksomhedsNR(input.next());
            System.out.println("Indsæt Virksomhedens CVR nummer: ");
            nyVirksomhed.setCvr(input.next());
            dbSql.opretVirksomhed(nyVirksomhed);
            System.out.println("Du er nu oprettet!");
        }
        else System.out.println("Det er ikke en muliged.");
    }

    public void opretTransaktion(Transaktion t){

            dbSql.opretTransaktion(t);
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
    public void visAnmodninger(Bruger modtager) {
        DBSQL dbsqlAnmod = new DBSQL();
        ArrayList<Transaktion> anmodninger = dbsqlAnmod.hentAnmodninger(modtager.getBrugerID());
        //databasen returner et array
        for (Transaktion t : anmodninger) {
            System.out.println(t);
        }
    }
    public void visHistorik(Bruger bruger){
        //ArrayList<Transaktion> historik = getDBSql().hentHistorikPerson(bruger.getBrugerID());
        //for (Transaktion transaktion : historik){
            //System.out.println(transaktion);

            //sorter så den nyeste står øverst
        }


    public void godkendAnmodning(Transaktion anmodning) {

        System.out.println(anmodning);
        System.out.println("Tast 1 for at godkende eller tast 2 for at annullere");
        Scanner input = new Scanner(System.in);
        int brugerInput = input.nextInt();
        if(brugerInput == 1) {
            System.out.println("Anmodning godkendt");
            getDBSql().opretTransaktion(anmodning);
            getDBSql().withdrawMoney(anmodning.getAfsender(),anmodning.getAmount());
            getDBSql().depositMoney(anmodning.getModtager(),anmodning.getAmount());
        }
        else if (brugerInput == 2){
            System.out.println("Anmodning annulleret");
            //getDBSql().sletAnmodning(transaktionID);
        }
    }



    public void logIn()
    {
        System.out.println("Velkommen til MobilBetal");
        System.out.println("Vil du logge ind som privat eller virksomhed");
        System.out.println("1. Privat");
        System.out.println("2. Virksomhed");
        Scanner input = new Scanner(System.in);
        int brugervalg = input.nextInt();

        if (brugervalg == 1){
            System.out.println("Indtast telefon nummer:");
            String telefonnummer = input.next();
            System.out.println("Indtast kode");
            String kode = input.next();
            Person loginPerson = new Person();
            loginPerson.setTelefonNR(telefonnummer);
            loginPerson.setKode(kode);
            Person databasePerson  = new Person();
            databasePerson.hentBruger(telefonnummer);
            if(loginPerson.getTelefonNR().equals(databasePerson.getTelefonNR())
                    && loginPerson.getKode().equals(databasePerson.getKode()))
            {
                Bruger loggetInd = aktivBruger;
                //loggedInd = oplysninger fra database
                System.out.println("Du er nu logget ind");
            }
            else System.out.println("Forkert telefonnummer eller kode");
        }
        if (brugervalg == 2){
            System.out.println("Indtast 5 cifret nummer:");
            String virksomhednummer = input.next();
            System.out.println("Indtast kode");
            String kode = input.next();
            Virksomhed loginVirksomhed = new Virksomhed();
            loginVirksomhed.setVirksomhedsNR(virksomhednummer);
            loginVirksomhed.setKode(kode);
            Virksomhed databaseVirksomhed = new Virksomhed();
            databaseVirksomhed.hentBruger(virksomhednummer);
            if(loginVirksomhed.getVirksomhedsNR().equals(databaseVirksomhed.getVirksomhedsNR())
                    && loginVirksomhed.getKode().equals(databaseVirksomhed.getKode()))
            {
                Bruger loggetInd = aktivBruger;
                // loggetInd = oplysninger fra databasen
                System.out.println("Du er nu logget ind");
            }
            else System.out.println("Forkert virksomhed nummer eller kode");
        }
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

    public DBSQL getDBSql(){
        return dbSql;
    }

    public boolean tjekBalance(Bruger bruger, double amount){
        bruger.getB().getBalance();
        if( bruger.getB().getBalance() >= amount){
            return true;
        }
        else{System.out.println("Beløbet overstiger din nuværende saldo");
            return false;
        }
    }

}
