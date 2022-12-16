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
        Virksomhed v1;
        p5 = dbsql.hentPerson("11111111");
        p6 = dbsql.hentPerson("22222222");
        p7 = dbsql.hentPerson("33333333");
        v1 = new Virksomhed("Shell","1234","123456","20202020");
        v1 =dbsql.hentVirksomhed("123456");

        Transaktion t5 = new Transaktion(p5,v1,10.0,new Date(),"Test11");
        dbsql.opretTransaktion(t5);

    }
}

