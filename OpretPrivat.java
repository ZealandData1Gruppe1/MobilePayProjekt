import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpretPrivat extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Opret bruger");

    JTextField bankKonto;
    JTextField navn;
    JTextField kode;
    JTextField cprNR;
    JTextField telefonNR;
    JButton opret;
    JPanel opretPanel;

    OpretPrivat() {

        label.setBounds(0, 0, 100, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 420);
        //frame.setLayout(new GridLayout(5,1));
        frame.setLayout(new FlowLayout());


        opretPanel = new JPanel();
        opretPanel.setLayout(new GridLayout(12,1));

        opretPanel.add(label);
        opretPanel.add(bankKonto = new JTextField("Kontonummer"));
        opretPanel.add(navn = new JTextField("Navn"));
        opretPanel.add(kode = new JTextField("Kode"));
        opretPanel.add(cprNR = new JTextField("Cpr Nummer"));
        opretPanel.add(telefonNR = new JTextField("Telefon Nummer"));
        opretPanel.add(opret = new JButton("Opret"));
        bankKonto.setPreferredSize(new Dimension(200,45));
        navn.setPreferredSize(new Dimension(200,45));

        opret.addActionListener(this);
        frame.add(opretPanel);
        frame.setVisible(true);

    }
    public void opret(){
        JOptionPane.showMessageDialog(null,"Bruger oprettet","Succes",JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
    }
    public void personFindes(){
        JOptionPane.showMessageDialog(null,"Bruger eksistere i forvejen","Fejl",JOptionPane.PLAIN_MESSAGE);
    }
    public void bankKontoFindesIkke(){
        JOptionPane.showMessageDialog(null,"Bank Konto eksistere ikke","Fejl",JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DBSQL dbsql = new DBSQL();

        if(e.getSource()==opret){
            String tjekTelefonNR = telefonNR.getText();
            Person p1 = dbsql.hentPerson(tjekTelefonNR);
            if (p1.getTelefonNR() == null){
                String kontoNR = bankKonto.getText();
                BankKonto b1 = dbsql.findBankKonto(kontoNR);
                if (b1 != null){
                    Person p2 = new Person(navn.getText(),kode.getText(),cprNR.getText(),telefonNR.getText());
                    dbsql.opretPerson(p2);
                    Person p3 = dbsql.hentPerson(p2.getTelefonNR());
                    dbsql.forbindBankKontoPerson(p3.getBrugerID(),kontoNR);
                    opret();}
                else bankKontoFindesIkke();
            }
            else{personFindes();
                }
        }
    }
}
