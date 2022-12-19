import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Collections;

public class Login extends JFrame implements ActionListener {
    JFrame frame;
    JLabel loginOverskrift;

    JPanel loginPanel;
    JTextField brugernavn;
    JTextField kode;
    JLabel brugernavnTekst;
    JLabel kodeTekst;
    JButton loginKnap;

    JPanel opretPanel;
    JButton opretPrivat;
    JButton opretVirksomhed;
    Login(){


        frame = new JFrame();
        frame.setTitle("Velkommen til MobilPay");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new GridLayout(2,1));
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setResizable(true);
        frame.setSize(650,650);

        loginOverskrift = new JLabel("Log ind");
        loginOverskrift.setHorizontalAlignment(JLabel.CENTER);

        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3,2));
        loginPanel.add(brugernavnTekst = new JLabel("Telefon Nummer"));
        loginPanel.add(kodeTekst = new JLabel(("Kode")));
        loginPanel.add(brugernavn = new JTextField());
        loginPanel.add(kode = new JTextField());
        loginPanel.add(loginKnap = new JButton("log Ind"));
        kode.setPreferredSize(new Dimension(200,45));
        brugernavn.setPreferredSize(new Dimension(200,45));

        opretPanel = new JPanel();
        opretPanel.add(opretPrivat= new JButton("Opret ny Privat bruger"));
        opretPanel.add(opretVirksomhed = new JButton("Opret ny Virksomheds bruger"));



        opretVirksomhed.addActionListener(this);
        opretPrivat.addActionListener(this);
        loginKnap.addActionListener(this);
        frame.add(loginOverskrift);
        frame.add(loginPanel);
        frame.add(opretPanel);



        frame.setVisible(true);


    }

    public ArrayList<Transaktion> flip(ArrayList<Transaktion> t)
    {
        Collections.reverse(t);

        return t;
    }
    private void forkertLogin(){
        JOptionPane forkertLogin = new JOptionPane("Bruger navn og/eller kode er forkert");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        DBSQL dbsql = new DBSQL();
        String loginNR = brugernavn.getText();
        String loginKode = kode.getText();
        String dbTelefonNR = "0";
        String dbKode = "0";
        int test = 0;
        Person p1 = new Person();
        Virksomhed v1 = new Virksomhed();
        if (e.getSource() == loginKnap) {

            if (loginNR.length() > 5) {
                p1 = dbsql.hentPerson(loginNR);
                dbTelefonNR = p1.getTelefonNR();
                dbKode = p1.getKode();
                test = 1;

            } else if (loginNR.length() == 5) {
                v1 = dbsql.hentVirksomhed(loginNR);
                dbTelefonNR = v1.getVirksomhedsNR();
                dbKode = v1.getKode();
                test = 2;
            }
            if (loginNR.equals(dbTelefonNR) && loginKode.equals(dbKode)) {

                if (test == 1) {
                    ArrayList<Transaktion> list =dbsql.hentHistorik(p1.getTelefonNR());
                    Collections.reverse(list);
                    StartSide startSide = new StartSide(list,dbsql.hentAnmodninger(p1.getTelefonNR()));
                    startSide.setAktivPerson(p1);



                }
                if (test == 2)
                {
                    ArrayList<Transaktion> listv =dbsql.hentHistorik(v1.getVirksomhedsNR());
                    Collections.reverse(listv);
                    StartSide startSide = new StartSide(listv,dbsql.hentAnmodninger(v1.getVirksomhedsNR()));
                    startSide.setAktivVirksomhed(v1);
                }

                //else{}
                //forkertLogin();}

            }
            if (e.getSource() == opretPrivat) {

                //frame.dispose();
                OpretPrivat opretPrivat = new OpretPrivat();
            }
            if (e.getSource() == opretVirksomhed) {
                //frame.dispose();
                OpretVirksomhed opretVirksomhed = new OpretVirksomhed();
            }

        }
    }
}
