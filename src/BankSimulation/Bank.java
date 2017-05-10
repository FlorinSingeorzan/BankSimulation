package BankSimulation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;

public class Bank implements Observer,BankProc,Serializable{

    private final static  long serialVersionUID=1234;
    private HashMap<Person,TreeSet<Account>> bankInfo;

    Bank(){
        bankInfo=new HashMap<>();
    }

    @Override
    public void addPerson(Person p) {
        assert isNotInHash(p.getIdentifier());
        bankInfo.put(p,new TreeSet<>());
        assert isInHash(p.getIdentifier());
    }

    @Override
    public void removePerson(String id) {
        assert isInHash(id);
        for(Person p:bankInfo.keySet()){
            if(p.getIdentifier().equals(id)){
                bankInfo.remove(p);
            }
        }
        assert isNotInHash(id);
    }

    @Override
    public boolean addAccount(Account a) {
        assert isNotInHash(a);
        a.addObserver(this);
        for(Person p:bankInfo.keySet()){
            if(p.getIdentifier().equals(a.getHolderIdentifier())){
                for(Account ac:bankInfo.get(p)) {
                    if (ac.getType()==a.getType()) {
                        return false;
                    }
                }
                bankInfo.get(p).add(a);
                assert isInHash(a);
                return true;
            }
        }
        return false;
    }

    @Override
    public void editPerson(Person person) {
        assert isInHash(person.getIdentifier());
        for(Person p:bankInfo.keySet()){
            if(p.getIdentifier().equals(person.getIdentifier())){
                p.setEmail(person.getEmail());
                p.setPhone(person.getPhone());
                p.setAddress(person.getAddress());
                p.setAge(person.getAge());
                p.setName(person.getName());
            }
        }
        assert  isInHash(person.getIdentifier());
    }

    @Override
    public void removeAccount(String id,int type) {
        assert isInHash(accountOf(id,type));
        for(Person p:bankInfo.keySet()){
            for(Account a:bankInfo.get(p))
                if(a.getHolderIdentifier().equals(id) && a.getType()==type){
                    bankInfo.get(p).remove(a);
                }
        }
        assert isNotInHash(accountOf(id,type));


    }

    @Override
    public int addMoney(String id, int j, int sum) {
        assert isInHash(accountOf(id,j));
        for(Person p:bankInfo.keySet()){
            if(p.getIdentifier().equals(id)) {
                for (Account a : bankInfo.get(p)) {
                    if(a.getType()==0 && a.getSold()!=0){return 0;}
                    if (a.getType()==1){
                        a.setSold(a.getSold()+sum);
                        return a.getSold();
                    }
                    if (a.getType()==0){
                        a.setSold((int)(a.getSold()+sum*1.05f));
                        return a.getSold();
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int takeMoney(String id, int j, int sum) {
        assert isInHash(accountOf(id,j));
        for(Person p:bankInfo.keySet()){
            if(p.getIdentifier().equals(id)) {
                for (Account a : bankInfo.get(p)) {
                    if (a.getType() == 0 && j==0 && a.getSold() != 0) {
                        a.setSold(0);
                        assert isPositive();
                        return 0;
                    } else if(a.getType()==1 && j==1){
                        a.setSold(a.getSold() - sum);
                        assert isAccountPositive(a);
                        assert isPositive();
                        return a.getSold();
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public boolean isInHash(String id) {
        for(Person p: bankInfo.keySet()){
            if(p.getIdentifier().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInHash(Account a) {
        for(Person p: bankInfo.keySet()){
            if(p.getIdentifier().equals(a.getHolderIdentifier()) && bankInfo.get(p).contains(a)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isNotInHash(String id) {
        for(Person p: bankInfo.keySet()){
            if(p.getIdentifier().equals(id)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isNotInHash(Account a) {
        for(Person p: bankInfo.keySet()){
            if(p.getIdentifier().equals(a.getHolderIdentifier()) && bankInfo.get(p).contains(a)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isPositive() {
        int sum=0;
        for(Person p: bankInfo.keySet()){
            for(Account a:bankInfo.get(p)) {
             sum+=a.getSold();
            }
        }
        return sum>=0;
    }

    @Override
    public boolean isAccountPositive(Account a) {
        return a.getSold() >= 0;
    }

    @Override
    public Account accountOf(String s, int t) {
        for(Person p: bankInfo.keySet()){
            for(Account a:bankInfo.get(p)) {
                if(a.getHolderIdentifier().equals(s) && a.getType()==t){
                    return a;
                }
            }
        }
        return null;
    }

    HashMap<Person, TreeSet<Account>> getBankInfo() {
        return bankInfo;
    }


    @Override
    public void update(Observable o, Object arg) {
        Account account=(Account)arg;
        double currentSold;
        if(account.getType()==0){
            currentSold=account.getSold()*1.05f;
        }
        else {
            currentSold=(double) account.getSold();
        }
        System.out.println("The person with identifier "+account.getHolderIdentifier()+" get his/her sold modified at "+currentSold+" for the account type: "+account.toString());
    }
}
