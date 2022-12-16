import java.util.Date;

public class Transaktion {
    private Bruger afsender;
    private Bruger modtager;
    private double amount;
    private int transaktionID;
    private Date dato;
    private String kommentar;

    public Transaktion(Bruger afsender, Bruger modtager, double amount, int transaktionID, Date dato, String kommentar) {
        this.afsender = afsender;
        this.modtager = modtager;
        this.amount = amount;
        transaktionID = transaktionID;
        this.dato = dato;
        this.kommentar = kommentar;
    }
    public Transaktion(Bruger afsender, Bruger modtager, double amount, Date dato, String kommentar) {
        this.afsender = afsender;
        this.modtager = modtager;
        this.amount = amount;
        this.dato = dato;
        this.kommentar = kommentar;
    }
    public Transaktion ()
    {

    }

    public Bruger getAfsender() {
        return afsender;
    }

    public void setAfsender(Bruger afsender) {
        this.afsender = afsender;
    }

    public Bruger getModtager() {
        return modtager;
    }

    public void setModtager(Bruger modtager) {
        this.modtager = modtager;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTransaktionID() {
        return transaktionID;
    }

    public void setTransaktionID(int transaktionID) {
        this.transaktionID = transaktionID;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    @Override
    public String toString() {
        return "Transaktion{" +
                "afsender=" + afsender +
                ", modtager=" + modtager +
                ", amount=" + amount +
                ", transaktionID=" + transaktionID +
                ", dato=" + dato +
                ", kommentar='" + kommentar + '\'' +
                '}';
    }
}
