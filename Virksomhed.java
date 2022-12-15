public class Virksomhed extends Bruger{
    private String virksomhedsNR;
    private String cvr;

    public Virksomhed(BankKonto b, String navn, int brugerID, String kode, String virksomhedsNR, String cvr) {
        super(b, navn, brugerID, kode);
        this.virksomhedsNR = virksomhedsNR;
        this.cvr = cvr;
    }

    public Virksomhed(BankKonto b, String navn, String kode, String virksomhedsNR, String cvr) {
        super(b, navn, kode);
        this.virksomhedsNR = virksomhedsNR;
        this.cvr = cvr;
    }
    public Virksomhed()
    {

    }

    public String getVirksomhedsNR() {
        return virksomhedsNR;
    }

    public void setVirksomhedsNR(String virksomhedsNR) {
        this.virksomhedsNR = virksomhedsNR;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }
}
