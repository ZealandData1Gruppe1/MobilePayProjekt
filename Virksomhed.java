public class Virksomhed extends Bruger{
    private String virksomhedsNR; //Virksomheden skal selv finde på et nummer
    private int cvr;

    public Virksomhed(BankKonto b, String navn, int brugerID, String kode, String virksomhedsNR, int cvr) {
        super(b, navn, brugerID, kode);
        this.virksomhedsNR = virksomhedsNR;
        this.cvr = cvr;
    }

    public Virksomhed(){

    }

    public Virksomhed(BankKonto b, String navn, String kode, String virksomhedsNR, int cvr) {
        super(b, navn, kode);
        this.virksomhedsNR = virksomhedsNR;
        this.cvr = cvr;
    }

    public String getVirksomhedsNR() {
        return virksomhedsNR;
    }

    public void setVirksomhedsNR(String virksomhedsNR) {
        this.virksomhedsNR = virksomhedsNR;
    }

    public int getCvr() {
        return cvr;
    }

    public void setCvr(int cvr) {
        this.cvr = cvr;
    }
}
