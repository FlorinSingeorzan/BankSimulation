package BankSimulation;


import java.io.*;

public class BankControl {
    private GraphicWindow view;
    private Bank bank;

    BankControl(GraphicWindow view, Bank bank) {
        this.view = view;
        this.bank = bank;
        BankActions.getAccount(view, bank);
        BankActions.deleteAccount(view, bank);
        BankActions.addNewPerson(view, bank);
        BankActions.deletePerson(view, bank);
        BankActions.editPerson(view, bank);
        BankActions.addSold(view, bank);
        BankActions.withdrawSold(view, bank);
        BankActions.autoFill(view);
    }

    public static void main(String[] args) {
        Bank bank;
        try {
            FileInputStream fileIn = new FileInputStream("bank.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            bank = (Bank) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }

        GraphicWindow showWindow = new GraphicWindow();
        BankActions.initializeTables(showWindow,bank);

        new BankControl(showWindow, bank);


        while (showWindow.isShowing()){}

        try {
            FileOutputStream fileOut = new FileOutputStream("bank.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(bank);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in bank.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


}

