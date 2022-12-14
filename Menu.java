import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    private DBSQL dbsql = new DBSQL();
    private Bruger aktivBruger;

    //Constructor

    public Menu(DBSQL dbsql) {
        this.dbsql = dbsql;
    }
    public Menu()
    {

    }

    //Metoder
    public void godkendTransaktion(Transaktion t)
    {

    }

    public void opretBruger()
    {
        System.out.println("Vil du oprette en privatbruger tryk 1");
        System.out.println("Vil du oprette en virksomhedsbruger tryk 2");
        Scanner input = new Scanner(System.in);
        int brugerInput = input.nextInt();
        if (brugerInput == 1){
            Person nyPerson = new Person();
            System.out.println("Indtast bankkonto");
            nyPerson.addBankKonto(nyPerson,input.next());//Kig på BankKonto addBankKonto, hvordan tilføjer vi bruger
            System.out.println("Indtast navn");
            nyPerson.setNavn(input.next());
            System.out.println("Indtast den ønskede kode");
            nyPerson.setKode(input.next());
            System.out.println("Indtast cpr nummer: ");
            nyPerson.setCpr(input.next());
            System.out.println("Indtast telefonnummer: ");
            nyPerson.setTelefonNR(input.next());
            //Opdatere databasen
            System.out.println("Du er nu oprettet!");

        } else if (brugerInput == 2)
        {
            Virksomhed nyVirksomhed = new Virksomhed();
            System.out.println("Indtast bankkonto");
            nyVirksomhed.addBankKonto(nyVirksomhed,input.next());//Kig på BankKonto addBankKonto, hvordan tilføjer vi bruger
            System.out.println("Indtast navn");
            nyVirksomhed.setNavn(input.next());
            System.out.println("Indsæt dit unikke 5 cifret nummer");// Virksomheden skal selv finde på et nummer
            nyVirksomhed.setVirksomhedsNR(input.next());
            System.out.println("Indsæt Virksomhedens CVR nummer: ");
            nyVirksomhed.setCvr(input.nextInt());
            //Opdatere databasen
            System.out.println("Du er nu oprettet!");
        }
        else System.out.println("Det er ikke en muliged.");
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
        DBSQL dbsqlAnmod = new DBSQL();
        ArrayList<Transaktion> anmodninger = dbsqlAnmod.hentAnmodninger(b.getBrugerID());
        //databasen returner et array
        for (Transaktion t : anmodninger){
            System.out.println(t);
        }
    }
    public void godkendAnmodning(Transaktion t)
    {
        
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
            //Send forespørgelse til database
            boolean forespørgelse = false;
            if (forespørgelse){
                Person loggetInd = new Person();
                // loggetInd = oplysninger fra databasen
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
            //Send forespørgelse til database
            boolean forespørgelse = false;
            if (forespørgelse){
                Virksomhed loggetInd = new Virksomhed();
                // loggetInd = oplysninger fra databasen
                System.out.println("Du er nu logget ind");
            }
            else System.out.println("Forkert telefonnummer eller kode");
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
}
