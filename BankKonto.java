public class BankKonto {
    private int bankKontoID;
    private double balance;
    private String kontoNR;

    public BankKonto(int bankKontoID, double balance, String kontoNR) {
        bankKontoID = bankKontoID;
        this.balance = balance;
        kontoNR = kontoNR;
    }
    public BankKonto()
    {


    }
    public BankKonto( double balance, String kontoNR) {
        this.balance = balance;
        kontoNR = kontoNR;
    }

    public int getBankKontoID() {
        return bankKontoID;
    }

    public void setBankKontoID(int bankKontoID) {
        this.bankKontoID = bankKontoID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getKontoNR() {
        return kontoNR;
    }

    public void setKontoNR(String kontoNR) {
        this.kontoNR = kontoNR;
    }

    @Override
    public String toString() {
        return "BankKonto{" +
                "bankKontoID=" + bankKontoID +
                ", balance=" + balance +
                ", kontoNR='" + kontoNR + '\'' +
                '}';
    }
}
