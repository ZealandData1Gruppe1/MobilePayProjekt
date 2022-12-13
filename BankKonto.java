public class BankKonto {
    private int BankKontoID;
    private double balance;
    private String KontoNR;

    public BankKonto(int bankKontoID, double balance, String kontoNR) {
        BankKontoID = bankKontoID;
        this.balance = balance;
        KontoNR = kontoNR;
    }
    public BankKonto()
    {


    }
    public BankKonto( double balance, String kontoNR) {
        this.balance = balance;
        KontoNR = kontoNR;
    }

    public int getBankKontoID() {
        return BankKontoID;
    }

    public void setBankKontoID(int bankKontoID) {
        BankKontoID = bankKontoID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getKontoNR() {
        return KontoNR;
    }

    public void setKontoNR(String kontoNR) {
        KontoNR = kontoNR;
    }
}
