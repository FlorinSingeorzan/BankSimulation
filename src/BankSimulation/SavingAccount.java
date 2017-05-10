package BankSimulation;
public class SavingAccount extends Account {

    private final static  long serialVersionUID=1238;
    private int interestRate=10;


    SavingAccount(){
        super();
        super.setType(0);
    }


    @Override
    public String getHolderIdentifier() {
        return super.getHolderIdentifier();
    }

    @Override
    public void setSold(int sold) {

        super.setSold(sold);
        setChanged();
        notifyObservers(this);
    }

    @Override
    public int getSold() {
        return super.getSold();
    }

    @Override
    public void setType(int type) {
        super.setType(type);
    }

    @Override
    public int getType() {
        return super.getType();
    }

    @Override
    public void setHolderIdentifier(String holderIdentifier) {
        super.setHolderIdentifier(holderIdentifier);
    }

    @Override
    public String toString() {
        return "-Saving Account-";
    }
}
