package BankSimulation;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

 class AccountWindow extends JFrame {

     private JPanel accountPanel;
     private JScrollPane tablePanel;
     private JTable accountsTable;
     private DefaultTableModel accountsModel;
     private JButton addAccount;
     private JButton deleteAccount;
     private JComboBox accountType;
     private JLabel[] info;
     private JTextField data;
     private Object[] identifier = {"Holder Id", "Type", "Sold", "Interest"};


     private void init() {
         accountPanel = new JPanel();
         accountsTable = new JTable() {
             public boolean isCellEditable(int row, int columns) {
                 return false;
             }
         };
         tablePanel = new JScrollPane(accountsTable);
         accountsModel = new DefaultTableModel();
         accountsTable.setModel(accountsModel);
         accountsModel.setColumnIdentifiers(identifier);
         initContainers();
     }

     @SuppressWarnings("unchecked")
     private void initContainers() {
         addAccount = new JButton("Add Account");
         deleteAccount = new JButton("Delete Account");
         info = new JLabel[2];
         data = new JTextField();
         info[0] = new JLabel("Holder Id:");
         info[1] = new JLabel("Account Type:");
         accountType = new JComboBox();
         accountType.addItem("Saving Account");
         accountType.addItem("Spending Account");

     }


     AccountWindow() {
         init();
         setFrame();

     }


     private void setFrame() {
         this.setSize(700, 600);
         this.setResizable(false);
         this.setTitle("Accounts");
         this.setVisible(false);
         this.setLayout(null);
         this.setLocation(0, 80);
         this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
         this.add(accountPanel);
         this.add(tablePanel);
         usePanels();
         addContainers();
         addButtons();
     }

     private void usePanels() {
         accountPanel.setLayout(null);
         accountPanel.setBounds(0, 400, 700, 200);
         tablePanel.setBounds(0, 0, 700, 400);
         accountPanel.setBackground(new Color(215, 215, 255));

     }


     private void addButtons() {
         addAccount.setBounds(80, 130, 120, 20);
         deleteAccount.setBounds(280, 130, 120, 20);
         accountPanel.add(addAccount);
         accountPanel.add(deleteAccount);
     }

     private void addContainers() {
         data.setBounds(80, 50, 100, 20);
         info[0].setBounds(80, 30, 120, 20);
         info[1].setBounds(230, 30, 120, 20);
         accountType.setBounds(230, 50, 150, 20);

         accountPanel.add(accountType);
         accountPanel.add(data);
         accountPanel.add(info[0]);
         accountPanel.add(info[1]);


     }

     void attachAddAccounts(ActionListener actionListener) {
         addAccount.addActionListener(actionListener);
     }

     void attachDeleteAccounts(ActionListener actionListener) {
         deleteAccount.addActionListener(actionListener);
     }


     void changeVisible(boolean b) {
         this.setVisible(b);
     }

     String getData() {
         return data.getText();
     }

     String getAccountType() {
         return (String) accountType.getSelectedItem();
     }

     DefaultTableModel getAccountsModel() {
         return accountsModel;
     }
 }
