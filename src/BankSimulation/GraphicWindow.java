package BankSimulation;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

 class GraphicWindow extends JFrame {
    private JButton toPerson;
    private JButton toAccount;
    private JPanel mainPanel;
    private AccountWindow accountWindow;
    private PersonWindow personWindow;
    private JLabel[] labels;
    private JTextField soldToAdd;
    private JTextField soldToWithdraw;
    private JTextField id;
    private JComboBox accountType;
    private JButton add;
    private JButton withdraw;


    private void init() {
        toPerson = new JButton("To Persons");
        toAccount = new JButton("To Accounts");
        mainPanel = new JPanel();
        accountWindow = new AccountWindow();
        personWindow = new PersonWindow();
        soldToAdd=new JTextField();
        soldToWithdraw=new JTextField();
        id=new JTextField();
        accountType=new JComboBox();
        labels=new JLabel[6];
        labels[0]=new JLabel("Choose Operation");
        labels[1]=new JLabel("Identifier");
        labels[2]=new JLabel("Sum to Withdraw");
        labels[3]=new JLabel("Choose account");
        labels[4]=new JLabel("Sum to Add");
        labels[5]=new JLabel("");
        add=new JButton("Add");
        withdraw=new JButton("Withdraw");
    }

    GraphicWindow() {
        init();
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Simulation");
        this.setVisible(true);
        this.setLocation(400, 80);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.add(mainPanel);
        setPanel();
    }

    private void setPanel() {
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(0x6E6C6D));
        mainPanel.add(toAccount);
        mainPanel.add(toPerson);
        mainPanel.add(accountType);
        mainPanel.add(soldToAdd);
        mainPanel.add(soldToWithdraw);
        mainPanel.add(add);
        mainPanel.add(withdraw);
        mainPanel.add(id);
        for(int i=0;i<6;i++){
            mainPanel.add(labels[i]);
        }
        showContainers();
    }

    private void showContainers() {
        toPerson.setBounds(230, 25, 120, 25);
        toAccount.setBounds(230, 70, 120, 25);
        add.setBounds(70,270,100,22);
        withdraw.setBounds(70,340,100,22);
        id.setBounds(70,180,110,22);
        soldToWithdraw.setBounds(420,250,110,22);
        accountType.setBounds(225,180,150,22);
        soldToAdd.setBounds(420,180,110,22);
        labels[0].setForeground(Color.WHITE);
        labels[0].setBounds(190,110,220,40);
        labels[0].setFont(new Font("Calibri",Font.BOLD,28));
        labels[1].setBounds(70,160,100,20);
        labels[2].setBounds(420,230,100,22);
        labels[3].setBounds(225,160,100,20);
        labels[4].setBounds(420,160,100,20);
        labels[5].setBounds(50,420,200,22);
        labels[5].setForeground(Color.WHITE.darker());
        toAccount.addActionListener(e -> accountWindow.changeVisible(true));
        toPerson.addActionListener(e -> personWindow.changeVisible(true));
        accountType.addItem("Saving Account");
        accountType.addItem("Spending Account");
    }

    String getId(){
        return id.getText();
    }

    String getSoldToAdd(){
        return soldToAdd.getText();
    }

    String getSoldToWithdraw(){
        return soldToWithdraw.getText();
    }

    String getAccountSelectedType(){
        return (String) accountType.getSelectedItem();
    }

    void addSold(ActionListener actionListener){add.addActionListener(actionListener);}

    void withdrawSold(ActionListener actionListener){withdraw.addActionListener(actionListener);}

    void addAccount(ActionListener actionListener) {
        accountWindow.attachAddAccounts(actionListener);
    }

    void deleteAccount(ActionListener actionListener) {
        accountWindow.attachDeleteAccounts(actionListener);
    }

    void addPerson(ActionListener actionListener) {
        personWindow.attachAddPerson(actionListener);
    }

    void deletePerson(ActionListener actionListener) {
        personWindow.attachDeletePerson(actionListener);
    }

    void editPerson(ActionListener actionListener) {
        personWindow.attachEditPerson(actionListener);
    }

    void clickListener(MouseListener t){personWindow.clickListener(t);}

    String getAccountsData() {
        return accountWindow.getData();
    }

    DefaultTableModel getAccountModel() {
        return accountWindow.getAccountsModel();
    }

    String getAccountType() {
        return accountWindow.getAccountType();
    }

    String getPersonData(int i) {
        return personWindow.getDate(i);
    }

    int selectedRow(){return personWindow.selectedRow();}

    boolean isReady() {
        return personWindow.isAllReady();
    }

    void setAtClick(int i,String s){
        personWindow.setAtClick(i,s);
    }

    DefaultTableModel getPersonModel() {
        return personWindow.getPersonModel();
    }
}
