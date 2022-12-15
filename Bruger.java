public class Bruger {
    private BankKonto b;

    private String navn;
    private int brugerID;
    private String kode;
   public Bruger()
   {

   }
    public Bruger(BankKonto b, String navn, int brugerID, String kode) {
        this.b = b;
        this.navn = navn;
        brugerID = brugerID;
        this.kode = kode;
    }
    public Bruger(BankKonto b, String navn, String kode) {
        this.b = b;
        this.navn = navn;
        this.kode = kode;
    }
    public Bruger(String navn, String kode) {
        this.navn = navn;
        this.kode = kode;
    }

    //Metoder

    public Bruger hentBruger(String telefonNR)
    {
        return new Bruger();
    }
    public void addBankKonto(Bruger b, String kontoNR)
    {

    }

    public boolean eksistererBruger(Bruger b)
    {
        return true;
    }

    //Getter og Setter
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
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
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

    @Override
    public String toString() {
        return "Bruger{" +
                "b=" + b +
                ", navn='" + navn + '\'' +
                ", brugerID=" + brugerID +
                ", kode='" + kode + '\'' +
                '}';
    }
}
