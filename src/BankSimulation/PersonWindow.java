package BankSimulation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class PersonWindow extends JFrame {

    private JPanel personPanel;
    private JScrollPane tablePanel;
    private JTable personsTable;
    private DefaultTableModel personModel;
    private JButton addPerson;
    private JButton editPerson;
    private JButton deletePerson;
    private JLabel[] info;
    private JTextField[] data;
    private Object[] identifier={"Identifier","Name","Age","Address","Email","Phone"};


    private void init(){
        personPanel=new JPanel();
        personsTable =new JTable(){
            public boolean isCellEditable(int row,int columns){return false;}
        };
        tablePanel=new JScrollPane(personsTable);
        personModel=new DefaultTableModel();
        personsTable.setModel(personModel);
        personModel.setColumnIdentifiers(identifier);
        initContainers();
    }

    private void initContainers(){
        int i;
        addPerson=new JButton("Add Person");
        deletePerson=new JButton("Delete Person");
        editPerson=new JButton("Edit Person");
        info=new JLabel[6];
        data=new JTextField[6];
        for(i=0;i<6;i++){
            data[i]=new JTextField();
        }
        info[0]=new JLabel("Holder Id:");
        info[1]=new JLabel("Name:");
        info[2]=new JLabel("Age:");
        info[3]=new JLabel("Address:");
        info[4]=new JLabel("Email:");
        info[5]=new JLabel("Phone:");

    }


    public PersonWindow(){
        init();
        setFrame();
    }


    private void setFrame(){
        this.setSize(700,600);
        this.setResizable(false);
        this.setTitle("Person");
        this.setVisible(false);
        this.setLayout(null);
        this.setLocation(660,80);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.add(personPanel);
        this.add(tablePanel);
        usePanels();
        addContainers();

    }

    private void usePanels(){
        personPanel.setLayout(null);
        tablePanel.setBounds(0,0,700,400);
        personPanel.setBounds(0,400,700,200);
        personPanel.setBackground(new Color(215,215,255));
        addButtons();
    }
    private void addContainers(){
        int i,xPos=20;
        for(i=0;i<6;i++){
            data[i].setBounds(xPos,70,100,20);
            info[i].setBounds(xPos,50,120,20);
            xPos+=110;
        }
        for(i=0;i<6;i++){
            personPanel.add(data[i]);
            personPanel.add(info[i]);
        }


    }
    private void addButtons(){
        addPerson.setBounds(90,130,120,20);
        deletePerson.setBounds(280,130,120,20);
        editPerson.setBounds(470,130,120,20);
        personPanel.add(addPerson);
        personPanel.add(editPerson);
        personPanel.add(deletePerson);
    }

    void changeVisible(boolean b){
        this.setVisible(b);
    }


    public String  getDate(int i){
        return data[i].getText();
    }


    public boolean isAllReady(){
        int i;
        for(i=0;i<6;i++){
            if (data[i].getText().equals("")){
                return false;
            }
        }
        return true;

    }

    void clickListener(MouseListener t){tablePanel.addMouseListener(t);}

     void attachAddPerson(ActionListener actionListener){
        addPerson.addActionListener(actionListener);
    }

     void attachDeletePerson(ActionListener actionListener){
        deletePerson.addActionListener(actionListener);
    }

    int selectedRow(){return personsTable.getSelectedRow();}

     void attachEditPerson(ActionListener actionListener){
        editPerson.addActionListener(actionListener);
    }

     DefaultTableModel getPersonModel() {
        return personModel;
    }

    void setAtClick(int i,String s){data[i].setText(s);}
}
