package BankSimulation;
public class SpendingAccount extends Account {

    private final static  long serialVersionUID=1239;
    SpendingAccount(){
        super();
        super.setType(1);
    }
    @Override
    public int getSold() {
        return super.getSold();
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

    public int getType() {
        return super.getType();
    }

    @Override
    public String toString() {
        return "-Spending-";
    }
}
