package BankSimulation;


import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.TreeSet;

 class BankActions {

    static void getAccount(GraphicWindow g, Bank b) {
        g.addAccount((ActionEvent e) -> {
            if (!g.getAccountsData().equals("")) {
                Account account = null;
                String holderId;
                holderId = g.getAccountsData();
                String accountType;
                accountType = g.getAccountType();
                String interest = "";
                if (accountType.equals("Saving Account")) {
                    interest = "5%";
                    account = new SavingAccount();
                    account.setSold(0);
                    account.setHolderIdentifier(holderId);
                } else if (accountType.equals("Spending Account")) {
                    interest = "0";
                    account = new SpendingAccount();
                    account.setSold(0);
                    account.setHolderIdentifier(holderId);
                }
                boolean canAdd = b.addAccount(account);
                if (canAdd) {g.getAccountModel().addRow(new String[]{holderId, accountType, "0", interest});}
            }
        });
    }

     static void deletePerson(GraphicWindow g, Bank b) {
        g.deletePerson((ActionEvent e) -> {
            if (!g.getPersonData(0).equals("")) {
                int i;
                String toDelete;
                toDelete = g.getPersonData(0);
                for (i = 0; i < 50; i++) {
                    if (g.getPersonModel().getRowCount() < 1) {break;}
                    try {
                        if (g.getPersonModel().getValueAt(i, 0).equals(toDelete)) {
                            g.getPersonModel().removeRow(i);
                            b.removePerson(toDelete);
                            break;
                        }
                    } catch (Exception ignored) {}
                }
                for (i = 0; i < 50; i++) {
                    if (g.getAccountModel().getRowCount() < 1) {break;}
                    try {
                        if (g.getAccountModel().getValueAt(i, 0).equals(toDelete)) {
                            g.getAccountModel().removeRow(i);
                            b.removeAccount(toDelete, 0);
                            b.removeAccount(toDelete, 1);
                        }
                    } catch (Exception ignored) {}
                }
            }
        });
    }


    static void deleteAccount(GraphicWindow g, Bank b) {
        g.deleteAccount(e -> {
            if (!g.getAccountsData().equals("")) {
                int i;
                String toDelete;
                toDelete = g.getAccountsData();
                String typeToDelete = g.getAccountType();
                for (i = 0; i < 50; i++) {
                    if (g.getAccountModel().getRowCount() < 1) {break;}
                    try {
                        if (g.getAccountModel().getValueAt(i, 0).equals(toDelete) && g.getAccountModel().getValueAt(i, 1).equals(typeToDelete)) {
                            g.getAccountModel().removeRow(i);
                            int j;
                            if (typeToDelete.equals("Saving Account")) {j = 0;
                            } else {j = 1;}
                            b.removeAccount(toDelete, j);
                            break;
                        }
                    } catch (Exception ignored) {}
                }
            }
        });
    }


    static void addNewPerson(GraphicWindow g, Bank b) {
        g.addPerson((ActionEvent e) -> {
            if (g.isReady()) {
                Person person = new Person();
                String holderId = g.getPersonData(0);
                String name = g.getPersonData(1);
                String age = g.getPersonData(2);
                String address = g.getPersonData(3);
                String email = g.getPersonData(4);
                String phone = g.getPersonData(5);
                person.setAge(Integer.parseInt(age));
                person.setIdentifier(holderId);
                person.setName(name);
                person.setAddress(address);
                person.setPhone(phone);
                person.setEmail(email);
                b.addPerson(person);
                g.getPersonModel().addRow(new String[]{holderId, name, age, address, email, phone});
            }
        });
    }
    static void editPerson(GraphicWindow g, Bank b) {
        g.editPerson((ActionEvent e) -> {
            if (g.isReady()) {
                Person person = new Person();
                String holderId = g.getPersonData(0);
                String name = g.getPersonData(1);
                String age = g.getPersonData(2);
                String address = g.getPersonData(3);
                String email = g.getPersonData(4);
                String phone = g.getPersonData(5);
                person.setAge(Integer.parseInt(age));
                person.setIdentifier(holderId);
                person.setName(name);
                person.setAddress(address);
                person.setPhone(phone);
                person.setEmail(email);
                int i;
                for (i = 0; i < 50; i++) {
                    if (g.getPersonModel().getRowCount() < 1) {break;}
                    try {
                        if (g.getPersonModel().getValueAt(i, 0).equals(holderId)) {
                            g.getPersonModel().removeRow(i);
                            break;
                        }
                    } catch (Exception ignored) {}
                }
                b.editPerson(person);
                g.getPersonModel().addRow(new String[]{holderId, name, age, address, email, phone});
            }
        });
    }

     static void withdrawSold(GraphicWindow g, Bank b) {
        g.withdrawSold((ActionEvent e) -> {
            if (!g.getId().equals("")) {
                String id = g.getId();
                String sumWithdraw = g.getSoldToWithdraw();
                String type = g.getAccountSelectedType();
                int j, i,k;
                String interest;
                if (type.equals("Saving Account")) {
                    j = 0;
                    interest = "5%";
                } else {
                    j = 1;
                    interest = "0";
                }
                k = b.takeMoney(id, j, Integer.parseInt(sumWithdraw));
                if(k!=-1) {
                    try {
                        for (i = 0; i < 50; i++) {
                            if (g.getAccountModel().getRowCount() < 1) {break;}
                            if (g.getAccountModel().getValueAt(i, 0).equals(id) && g.getAccountModel().getValueAt(i, 1).equals(type)) {
                                g.getAccountModel().removeRow(i);
                                break;
                            }}
                        g.getAccountModel().addRow(new String[]{id, type, k + "", interest});
                    }catch (Exception ignored) {}}
            }
        });
    }
    static void addSold(GraphicWindow g, Bank b) {
        g.addSold(e -> {
        if (!g.getId().equals("")) {
            String sumAdd = g.getSoldToAdd();
            String id = g.getId();
            String type = g.getAccountSelectedType();
            int j, i,k;
            String interest;
            if (type.equals("Saving Account")) {
                j = 0;
                interest = "5%";
            } else {
                j = 1;
                interest = "0";
            }
            k = b.addMoney(id, j, Integer.parseInt(sumAdd));
            if(k!=0) {
                try {
                    for (i = 0; i < 50; i++) {
                        if (g.getAccountModel().getRowCount() < 1) {break;}
                        if (g.getAccountModel().getValueAt(i, 0).equals(id) && g.getAccountModel().getValueAt(i, 1).equals(type)) {
                            g.getAccountModel().removeRow(i);
                            break;
                        }}
                    g.getAccountModel().addRow(new String[]{id, type, k + "", interest});
                }catch (Exception ignored) {}}
        }
        });
    }


    static void initializeTables(GraphicWindow g,Bank b){
        HashMap<Person,TreeSet<Account>> toInitialize;
        toInitialize=b.getBankInfo();
        for(Person p:toInitialize.keySet()){
            for(Account a:toInitialize.get(p)){
                String interest,type;
                if(a.getType()==0){
                    interest="5%";
                    type="Saving Account";
                } else{
                    interest="0";
                    type="Spending Account";
                }
                g.getAccountModel().addRow(new String[]{a.getHolderIdentifier(),type,a.getSold()+"",interest});
                a.addObserver(b);
            }
            g.getPersonModel().addRow(new String[]{p.getIdentifier(),p.getName(),p.getAge()+"",p.getAddress(),p.getEmail(),p.getPhone()});
        }
    }
    static void autoFill(GraphicWindow g){
        g.clickListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i;
                if(g.selectedRow()>=0) {
                    for (i = 0; i < 6; i++) {g.setAtClick(i, (String) g.getPersonModel().getValueAt(g.selectedRow(), i));}
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

    }
}