public class Bruger {
    private BankKonto b;
    private String navn;
    private int BrugerID;
    private String kode;
   public Bruger()
   {

   }
    public Bruger(BankKonto b, String navn, int brugerID, String kode) {
        this.b = b;
        this.navn = navn;
        BrugerID = brugerID;
        this.kode = kode;
    }
    public Bruger(BankKonto b, String navn, String kode) {
        this.b = b;
        this.navn = navn;
        this.kode = kode;
    }
    public BankKonto getB() {
        return b;
    }

    public void setB(BankKonto b) {
        this.b = b;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getBrugerID() {
        return BrugerID;
    }

    public void setBrugerID(int brugerID) {
        BrugerID = brugerID;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public boolean tjekSaldo(Bruger b)
    {

        return true;
    }

}
