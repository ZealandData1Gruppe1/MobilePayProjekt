public class Person extends Bruger {
    private String cpr;
    private String telefonNR;

    public Person()
    {

    }

    public Person(BankKonto b, String navn, int brugerID, String kode, String cpr, String telefonNR) {
        super(b, navn, brugerID, kode);
        this.cpr = cpr;
        this.telefonNR = telefonNR;
    }

    public Person(BankKonto b, String navn, String kode, String cpr, String telefonNR) {
        super(b, navn, kode);
        this.cpr = cpr;
        this.telefonNR = telefonNR;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getTelefonNR() {
        return telefonNR;
    }

    public void setTelefonNR(String telefonNR) {
        this.telefonNR = telefonNR;
    }
}
