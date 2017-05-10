package BankSimulation;



import java.io.Serializable;

public class Account extends java.util.Observable implements Comparable,Serializable{

    private final static  long serialVersionUID=1235;
    private int sold=0;
    private String holderIdentifier;
    private int type;



    public int getSold() {
        return sold;
    }

    public String getHolderIdentifier() {
        return holderIdentifier;
    }

    public void setSold(int sold) {
        this.sold = sold;

    }

    public void setHolderIdentifier(String holderIdentifier) {
        this.holderIdentifier = holderIdentifier;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {
        return ((Account)o).getType()-this.getType();
    }

    public void setType(int type) {
        this.type = type;
    }
}
