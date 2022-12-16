import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DBSQL dbsql = new DBSQL();
        ArrayList<Transaktion> test = new ArrayList<Transaktion>();
        /*
        Person p1 =new Person("Kevin","1234","1234567","11111111");
        Person p2 =new Person("Emil","0000","2345678","22222222");
        Person p3 =new Person("Jakob","1234","12346156","33333333");
        Person p4 =new Person("Sercan","1234","653465435","44444444");

        dbsql.opretPerson(p1);
        dbsql.opretPerson(p2);
        dbsql.opretPerson(p3);
        dbsql.opretPerson(p4);
        */

        Person p5 = new Person();
        Person p6 = new Person();
        Person p7 = new Person();
        Virksomhed v1 = new Virksomhed();
        p5 = dbsql.hentPerson("11111111");
        p6 = dbsql.hentPerson("22222222");
        p7 = dbsql.hentPerson("33333333");
        v1 = dbsql.hentVirksomhed("12345");

        /*Transaktion t1 = new Transaktion(p5, p6, 500, new Date(), "Test 1");
        Transaktion t2 = new Transaktion(p5, p7, 500, new Date(), "Test 2");
        Transaktion t3 = new Transaktion(p5, p8, 500, new Date(), "Test 3");
        Transaktion t4 = new Transaktion(p5, p6, 500, new Date(), "Test 4");
        Transaktion t5 = new Transaktion(p5, p6, 500, new Date(), "Test 5");
        Transaktion t6 = new Transaktion(p5, p7, 500, new Date(), "Test 6");
        Transaktion t7 = new Transaktion(p5, p7, 500, new Date(), "Test 7");
        Transaktion t8 = new Transaktion(p7, p5, 500, new Date(), "Test 8");
        Transaktion t9 = new Transaktion(p8, p5, 500, new Date(), "Test 9");
        Transaktion t10 = new Transaktion(p6, p5, 500, new Date(), "Test 10");

        dbsql.opretTransaktion(t1);
        dbsql.opretTransaktion(t2);
        dbsql.opretTransaktion(t3);
        dbsql.opretTransaktion(t4);
        dbsql.opretTransaktion(t5);
        dbsql.opretTransaktion(t6);
        dbsql.opretTransaktion(t7);
        dbsql.opretTransaktion(t8);
        dbsql.opretTransaktion(t9);
        dbsql.opretTransaktion(t10);*/

        test = dbsql.hentHistorik(p5.getBrugerID());
        for(int i =0; i<test.size();i++) {
            System.out.print("Transaktion nr : "+i);
            System.out.print(" Afsender: "+test.get(i).getAfsender());
            System.out.println(" Modtager: " +test.get(i).getModtager());
        }


        BankKonto b1 = dbsql.findBankKonto("12");
        dbsql.forbindBankKontoPerson(p5.getBrugerID(),b1.getKontoNR());

        BankKonto b2 = dbsql.findBankKonto("123456");
        dbsql.forbindBankKontoPerson(p6.getBrugerID(), b2.getKontoNR());


        BankKonto b3 = dbsql.findBankKonto("987654");
        dbsql.forbindBankKontoVirksomhed(v1.getBrugerID(),b3.getKontoNR());

        Transaktion t2 = new Transaktion();
        t2.setTransaktionID(1);
        dbsql.sletAnmodning(t2);


    }
}

