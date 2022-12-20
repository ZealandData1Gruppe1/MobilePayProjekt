import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class StartSide extends JFrame implements ActionListener {
    JFrame frame;
    JPanel sendPengePanel;
    JTextField modtager;
    JTextField amount;
    JTextField kommentar;
    JTextField dato;

    JLabel overskriftSendeAnmodning;
    JPanel AnmodningPanel;
    JTextField modtagerAnmodning;
    JTextField amountAnmodning;
    JTextField kommentarAnmodning;
    JTextField datoAnmoding;
    JButton anmondingKnap;
    JLabel overskriftSendPengePanel;

    JButton sendPengKnap;

    JLabel overskriftVisAnmodningPanel;
    JLabel overskriftVisAnmodningPanelTom;
    JPanel visAnmodningPanel;
    JLabel visAnmodnigLabel1;
    JLabel visAnmodnigLabel2;
    JLabel visAnmodnigLabel3;

    JButton visAnmodningKnap1;
    JButton visAnmodningKnap2;
    JButton visAnmodningKnap3;

    JPanel visHistorikPanel;
    JLabel visHistorikOverskrift;
    JLabel visHistorikOverskriftTom;
    JLabel visHistorik1;
    JLabel visHistorik2;
    JLabel visHistorik3;

    JButton test;
    Person aktivPerson = new Person();
    Virksomhed aktivVirksomhed = new Virksomhed();

    ArrayList<Transaktion> historikliste = new ArrayList<>();

    ArrayList<Transaktion> anmodningliste = new ArrayList<>();

    public Person getAktivPerson() {
        return aktivPerson;
    }

    public void setAktivPerson(Person aktivPerson) {
        this.aktivPerson = aktivPerson;
    }

    public Virksomhed getAktivVirksomhed() {
        return aktivVirksomhed;
    }

    public void setAktivVirksomhed(Virksomhed aktivVirksomhed) {
        this.aktivVirksomhed = aktivVirksomhed;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public ArrayList<Transaktion> getHistorikliste() {
        return historikliste;
    }

    public void setHistorikliste(ArrayList<Transaktion> historikliste) {
        this.historikliste = historikliste;
    }

    public ArrayList<Transaktion> getAnmodningliste() {
        return anmodningliste;
    }

    public void setAnmodningliste(ArrayList<Transaktion> anmodningliste) {
        this.anmodningliste = anmodningliste;
    }

    StartSide(ArrayList<Transaktion> h, ArrayList<Transaktion> a) {
        historikliste = h;
        anmodningliste = a;
        Person p1 = new Person("", "", "", "");
        Person p2 = new Person("", "", "", "");

        Transaktion h1 = new Transaktion(p1, p2, 0.0, new Date(), "");
        Transaktion h2 = new Transaktion(p1, p2, 0.0, new Date(), "");
        Transaktion h3 = new Transaktion(p1, p2, 0.0, new Date(), "");
        if (h.size() > 2) {
            h1 = h.get(0);
            h2 = h.get(1);
            h3 = h.get(2);
        }
        if (h.size() == 2) {
            h1 = h.get(0);
            h2 = h.get(1);
        }
        if (h.size() == 1) {
            h1 = h.get(0);
        }
        Transaktion a1 = new Transaktion(p1, p2, 0.0, new Date(), "");
        Transaktion a2 = new Transaktion(p1, p2, 0.0, new Date(), "");
        Transaktion a3 = new Transaktion(p1, p2, 0.0, new Date(), "");
        if (a.size() > 2) {
            a1 = a.get(0);
            a2 = a.get(1);
            a3 = a.get(2);
        }
        if (a.size() == 2) {
            a1 = h.get(0);
            a2 = h.get(1);
        }
        if (a.size() == 1) {
            a1 = h.get(0);
        }

        frame = new JFrame();
        frame.setTitle("Velkommen til MobilPay");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(true);
        frame.setSize(650, 650);

        //Send Penge panelet
        sendPengePanel = new JPanel();
        overskriftSendPengePanel = new JLabel("Send Penge");
        sendPengePanel.setBounds(0, 0, 30, 60);
        modtager = new JTextField("Modtager");
        amount = new JTextField("Beløb");
        kommentar = new JTextField("Kommentar");
        dato = new JTextField("Dato");

        sendPengKnap = new JButton("Send Peng");
        sendPengePanel.add(overskriftSendPengePanel);
        sendPengePanel.add(modtager);
        sendPengePanel.add(amount);
        sendPengePanel.add(kommentar);
        sendPengePanel.add(dato);
        sendPengePanel.add(sendPengKnap);
        modtager.setPreferredSize(new Dimension(64, 25));
        amount.setPreferredSize(new Dimension(64, 25));
        kommentar.setPreferredSize(new Dimension(64, 25));
        dato.setPreferredSize(new Dimension(64, 25));
        sendPengKnap.setPreferredSize(new Dimension(80, 25));

        // Vis Anmodninger
        visAnmodningPanel = new JPanel();
        overskriftSendPengePanel = new JLabel("Anmodninger");
        overskriftVisAnmodningPanelTom = new JLabel("");
        visAnmodnigLabel1 = new JLabel(printflot(a1));
        visAnmodnigLabel2 = new JLabel(printflot(a2));
        visAnmodnigLabel3 = new JLabel(printflot(a3));

        visAnmodningKnap1 = new JButton("Bekæft anmodning");
        visAnmodningKnap2 = new JButton("Bekæft anmodning");
        visAnmodningKnap3 = new JButton("Bekæft anmodning");
        visAnmodningPanel.add(overskriftSendPengePanel);
        visAnmodningPanel.add(overskriftVisAnmodningPanelTom);
        visAnmodningPanel.add(visAnmodnigLabel1);
        visAnmodningPanel.add(visAnmodningKnap1);
        visAnmodningPanel.add(visAnmodnigLabel2);
        visAnmodningPanel.add(visAnmodningKnap2);
        visAnmodningPanel.add(visAnmodnigLabel3);
        visAnmodningPanel.add(visAnmodningKnap3);
        visAnmodningPanel.setLayout(new GridLayout(4, 2));
        visAnmodningKnap1.addActionListener(this);
        visAnmodningKnap2.addActionListener(this);
        visAnmodningKnap3.addActionListener(this);

        //Vis Historik
        visHistorikPanel = new JPanel();
        visHistorikOverskrift = new JLabel("Historik");
        visHistorikOverskriftTom = new JLabel("");
        visHistorik1 = new JLabel(printflot(h1));
        visHistorik2 = new JLabel(printflot(h2));
        visHistorik3 = new JLabel(printflot(h3));
        visHistorikPanel.add(visHistorikOverskrift);
        //visHistorikPanel.add(visHistorikOverskriftTom);
        visHistorikPanel.add(visHistorik1);
        visHistorikPanel.add(visHistorik2);
        visHistorikPanel.add(visHistorik3);
        visHistorikPanel.setLayout(new GridLayout(4, 1, 20, 0));

        //Send Anmodning panelet
        AnmodningPanel = new JPanel();
        overskriftSendeAnmodning = new JLabel("Send Anmodning");
        anmondingKnap = new JButton("Send Anmodning");
        AnmodningPanel.setBounds(0, 0, 30, 60);
        modtagerAnmodning = new JTextField("Modtager");
        amountAnmodning = new JTextField("Beløb");
        kommentarAnmodning = new JTextField("Kommentar");
        datoAnmoding = new JTextField("Dato");
        //AnmodningPanel.add(overskriftVisAnmodningPanel);
        AnmodningPanel.add(modtagerAnmodning);
        AnmodningPanel.add(amountAnmodning);
        AnmodningPanel.add(kommentarAnmodning);
        AnmodningPanel.add(datoAnmoding);
        AnmodningPanel.add(anmondingKnap);

        //Det er tilføjes på frame
        anmondingKnap.addActionListener(this);
        sendPengKnap.addActionListener(this);
        frame.add(sendPengePanel);
        frame.add(visAnmodningPanel);
        frame.add(visHistorikPanel);
        frame.add(overskriftSendeAnmodning);
        frame.add(AnmodningPanel);
        frame.setVisible(true);
    }
    double amountRecieved;
    double amoutAnmodetAmount;
    DBSQL dbsql = new DBSQL();
    // ActionListener
    @Override           // Der skal være if else statements hvis der ikke er nok penge på kontoen.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendPengKnap) {
            String tjekModtager = modtager.getText();
            Virksomhed v1 = new Virksomhed();
            Person p1 = dbsql.hentPerson(tjekModtager);
            amountRecieved = Double.parseDouble(amount.getText());
            if (aktivPerson.getB().getBalance() > amountRecieved) {
                if (tjekModtager.length() == 5) {
                    v1 = dbsql.hentVirksomhed(tjekModtager);
                    Transaktion t1 = new Transaktion(getAktivPerson(), v1, amountRecieved, new Date(), kommentar.getText());
                    dbsql.opretTransaktion(t1);
                    dbsql.withdrawMoney(aktivPerson, amountRecieved);
                    dbsql.depositMoney(v1, amountRecieved);
                }
                if (tjekModtager.length() > 5) {
                    Transaktion t1 = new Transaktion(getAktivPerson(),p1,amountRecieved,new Date(),kommentar.getText());
                    dbsql.opretTransaktion(t1);
                    dbsql.withdrawMoney(aktivPerson, amountRecieved);
                    dbsql.depositMoney(p1, amountRecieved);
                }
                setAktivPerson(dbsql.hentPerson(aktivPerson.getTelefonNR()));
                JOptionPane.showMessageDialog(null, "Penge Sendt!", "Succes", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Du har ikke nok penge på kontoen", "Du er fattig", JOptionPane.PLAIN_MESSAGE);
            }
        }
        if (e.getSource() == visAnmodningKnap1) {
            Transaktion anmodning1 = new Transaktion();
            anmodning1 = anmodningliste.get(0);
            String modtager = modtagerAnmodning.getText();
            Person modtagerPerson = dbsql.hentPerson(modtager);
            if (aktivPerson.getB().getBalance() > anmodning1.getAmount()) {
                dbsql.opretTransaktion(anmodning1);
                dbsql.withdrawMoney(aktivPerson, anmodning1.getAmount());
                dbsql.depositMoney(modtagerPerson, anmodning1.getAmount());
                dbsql.sletAnmodning(anmodning1);
                setAktivPerson(dbsql.hentPerson(aktivPerson.getTelefonNR()));
                JOptionPane.showMessageDialog(null, "Penge Sendt!", "Succes", JOptionPane.PLAIN_MESSAGE);
                visAnmodnigLabel1.setVisible(false);
            } else
                JOptionPane.showMessageDialog(null, "Du har ikke nok penge på kontoen", "Du er fattig", JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == visAnmodningKnap2) {
            Transaktion anmodning1 = new Transaktion();
            anmodning1 = anmodningliste.get(1);
            String modtager = modtagerAnmodning.getText();
            Person modtagerPerson = dbsql.hentPerson(modtager);
            if (aktivPerson.getB().getBalance() > anmodning1.getAmount()) {
                dbsql.opretTransaktion(anmodning1);
                dbsql.withdrawMoney(aktivPerson, anmodning1.getAmount());
                dbsql.depositMoney(modtagerPerson, anmodning1.getAmount());
                dbsql.sletAnmodning(anmodning1);
                setAktivPerson(dbsql.hentPerson(aktivPerson.getTelefonNR()));
                JOptionPane.showMessageDialog(null, "Penge Sendt!", "Succes", JOptionPane.PLAIN_MESSAGE);
                visAnmodnigLabel2.setVisible(false);
            } else
                JOptionPane.showMessageDialog(null, "Du har ikke nok penge på kontoen", "Du er fattig", JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == visAnmodningKnap3) {
            Transaktion anmodning1 = new Transaktion();
            anmodning1 = anmodningliste.get(2);
            String modtager = modtagerAnmodning.getText();
            Person modtagerPerson = dbsql.hentPerson(modtager);
            if (aktivPerson.getB().getBalance() > anmodning1.getAmount()) {
                dbsql.opretTransaktion(anmodning1);
                dbsql.withdrawMoney(aktivPerson, anmodning1.getAmount());
                dbsql.depositMoney(modtagerPerson, anmodning1.getAmount());
                dbsql.sletAnmodning(anmodning1);
                setAktivPerson(dbsql.hentPerson(aktivPerson.getTelefonNR()));
            JOptionPane.showMessageDialog(null, "Penge Sendt!", "Succes", JOptionPane.PLAIN_MESSAGE);
            visAnmodnigLabel3.setVisible(false);
            } else
                JOptionPane.showMessageDialog(null, "Du har ikke nok penge på kontoen", "Du er fattig", JOptionPane.PLAIN_MESSAGE);
        }
        if(e.getSource()== anmondingKnap){
            String anmodingModtager = modtagerAnmodning.getText();
            Person p2 = new Person();
            String input = "+"+amountAnmodning.getText()+"d";
            amoutAnmodetAmount =Double.parseDouble(input);
            System.out.println(amoutAnmodetAmount);
            p2 = dbsql.hentPerson(anmodingModtager);
            System.out.println(p2);
            Transaktion t3 = new Transaktion(p2,getAktivPerson(),amoutAnmodetAmount,new Date(),kommentarAnmodning.getText());
            dbsql.opretAnmodningFraPerson(t3);
            JOptionPane.showMessageDialog(null,"Anmodning Sendt!","Succes",JOptionPane.PLAIN_MESSAGE);
        }
    }
        public String printflot (Transaktion t){
            String afsenderNavn = t.getAfsender().getNavn();
            String modtagerNavn = t.getModtager().getNavn();
            double amount = t.getAmount();
            String kommentar = t.getKommentar();
            String historik = "Fra: " + afsenderNavn + " " + "Til: " + modtagerNavn + " Beløb: " + amount + "DKK " + " " + kommentar;
            return historik;
        }

}

