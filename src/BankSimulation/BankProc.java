package BankSimulation;
public interface BankProc {

    void addPerson(Person p);
    void removePerson(String id);
    boolean addAccount(Account a);
    void removeAccount(String id,int type);
    void editPerson(Person person);
    int addMoney(String id, int j, int i);
    int takeMoney(String id, int j, int i);
    boolean isInHash(String id);
    boolean isInHash(Account a);
    boolean isNotInHash(String id);
    boolean isNotInHash(Account a);
    boolean isPositive();
    boolean isAccountPositive(Account a);
    Account accountOf(String s,int t);


}
