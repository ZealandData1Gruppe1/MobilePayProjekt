import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpretVirksomhed extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Opret bruger");

    JTextField bankKonto;
    JTextField navn;
    JTextField kode;
    JTextField cvrNummer;
    JTextField femCifretNummer;
    JButton opret;
    JPanel opretPanel;

    OpretVirksomhed() {

        label.setBounds(0, 0, 100, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 420);
        //frame.setLayout(new GridLayout(5,1));
        frame.setLayout(new FlowLayout());


        opretPanel = new JPanel();
        opretPanel.setLayout(new GridLayout(12, 1));

        opretPanel.add(label);
        opretPanel.add(bankKonto = new JTextField("Kontonummer"));
        opretPanel.add(navn = new JTextField("Navn"));
        opretPanel.add(kode = new JTextField("Kode"));
        opretPanel.add(cvrNummer = new JTextField("CVR Nummer"));
        opretPanel.add(femCifretNummer = new JTextField("5 cifret nummer"));
        opretPanel.add(opret = new JButton("Opret"));
        bankKonto.setPreferredSize(new Dimension(200, 45));
        navn.setPreferredSize(new Dimension(200, 45));

        opret.addActionListener(this);
        frame.add(opretPanel);
        frame.setVisible(true);
    }

    public void opret() {
        JOptionPane.showMessageDialog(null, "Bruger oprettet", "Succes", JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
    }

    public void virksomhedFindes() {
        JOptionPane.showMessageDialog(null, "Bruger eksistere i forvejen", "Fejl", JOptionPane.PLAIN_MESSAGE);
    }

    public void bankKontoFindesIkke() {
        JOptionPane.showMessageDialog(null, "Bank Konto eksistere ikke", "Fejl", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DBSQL dbsql = new DBSQL();

        if (e.getSource() == opret) {
            String tjekTelefonNR = femCifretNummer.getText();
            Virksomhed v1 = dbsql.hentVirksomhed(tjekTelefonNR);
            if (v1.getVirksomhedsNR() == null) {
                String kontoNR = bankKonto.getText();
                BankKonto b1 = dbsql.findBankKonto(kontoNR);
                if (b1 != null) {
                    Virksomhed v2 = new Virksomhed(navn.getText(), kode.getText(), femCifretNummer.getText(), cvrNummer.getText());
                    dbsql.opretVirksomhed(v2);
                    Virksomhed v3 = dbsql.hentVirksomhed(v2.getVirksomhedsNR());
                    dbsql.forbindBankKontoVirksomhed(v3.getBrugerID(), kontoNR);
                    opret();
                } else bankKontoFindesIkke();
            } else {
               virksomhedFindes();
            }
            }
        }
    }

