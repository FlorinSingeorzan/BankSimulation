package BankSimulation;


import junit.framework.TestCase;

public class BankJUnit extends TestCase {

    private Bank bank=new Bank();
    private Person person=new Person();
    private Account account=new Account();

    public void testAddMoney(){         //incepe cu test
        person.setName("Alexandri");
        person.setAge(23);
        person.setEmail("alex@gmail.com");
        person.setPhone("078232922");
        person.setAddress("Cluj");
        person.setIdentifier("1");
        account.setHolderIdentifier("1");
        account.setType(0);
        account.setSold(0);
        bank.addPerson(person);
        bank.addAccount(account);
        bank.addMoney("1",0,20);
        assertTrue(account.getSold()==21);

    }

    public void testWithdrawMoney(){         //incepe cu test
        person.setName("Alexandri");
        person.setAge(23);
        person.setPhone("078232922");
        person.setEmail("alex@gmail.com");
        person.setAddress("Cluj");
        person.setIdentifier("1");
        account.setHolderIdentifier("1");
        account.setType(0);
        account.setSold(0);
        bank.addPerson(person);
        bank.addAccount(account);
        bank.addMoney("1",0,20);
        bank.takeMoney("1",0,10);
        assertTrue(account.getSold()==0);

    }

    public void testWithdrawMoney2(){         //incepe cu test
        person.setName("Alexandri");
        person.setAge(23);
        person.setPhone("078232922");
        person.setEmail("alex@gmail.com");
        person.setAddress("Cluj");
        person.setIdentifier("1");
        account.setHolderIdentifier("1");
        account.setType(1);
        account.setSold(0);
        bank.addPerson(person);
        bank.addAccount(account);
        bank.addMoney("1",1,20);
        bank.takeMoney("1",1,10);
        assertTrue(account.getSold()==10);

    }

    public void testAddMoney2(){         //incepe cu test
        person.setName("Alexandri");
        person.setAge(23);
        person.setPhone("078232922");
        person.setEmail("alex@gmail.com");
        person.setAddress("Cluj");
        person.setIdentifier("1");
        account.setHolderIdentifier("1");
        account.setType(1);
        account.setSold(0);
        bank.addPerson(person);
        bank.addAccount(account);
        bank.addMoney("1",1,20);
        assertTrue(account.getSold()==20);

    }


}
