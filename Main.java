import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DBSQL dbsql = new DBSQL();
//        Person p2 = dbsql.hentPerson("28916233");
//        System.out.println(p2);
        //new Login();
        //Person p3 = new Person("JakobF","1234","0102031238","2891326565");
        //dbsql.opretPerson(p3);
        ArrayList<Transaktion> list = dbsql.hentHistorik(111    );
        System.out.println(list);


    }
}

