import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.*;

public class MainOrderWindow extends JPanel {

    public double totalOrderTotal = 0.0;

    private double subOrderTotal = 0.0;
    private int itemCount = 0; // holds the counter for items in the list
    private ArrayList<String> orderList = new ArrayList<>();
    private ArrayList<String> uniqueItemList = new ArrayList<>();
    private ArrayList<String> discountList = new ArrayList<>();
    private ArrayList<String> sandwichList = new ArrayList<>();


    private String display = "";
    private JButton button;
    public JPanel mainPanel;
    private Component mainmenu;

    private TableSubOrder indOrderTable = new TableSubOrder();
    static TableFullOrder subOrderTable = new TableFullOrder();

    static ArrayList<PersonalOrder> finalPersonalOrderList = new ArrayList<>();
    private ArrayList<FinalOrder> subOrderList = new ArrayList<>();
    private String subOrderName;

    Discount discount = new Discount();
    private double discountAmount;

    public MainOrderWindow(JPanel mainPanel) {
        // main panel initialize and add
        this.mainPanel = mainPanel;
        setBackground(Color.ORANGE);
        mainmenu = add(MainMenu());
        //these are the buttons to advance cards
        add(returnButton());
        add(payButton());
    }

    private JButton returnButton() {
        button = new JButton("Return to Login");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu_V1 main = new MainMenu_V1();
                main.switchPanel(mainPanel, "LOGIN");
            }
        });
        return button;
    }

    private JButton payButton() {
        button = new JButton("PAY");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GOTO pay card
                MainMenu_V1 main = new MainMenu_V1();
                //set pay cardlayout here to make sure variables update
                mainPanel.add(new PaymentrWindow(mainPanel), "PAYWINDOW");
                main.revalidate();
                main.switchPanel(mainPanel, "PAYWINDOW");

            }
        });

        return button;
    }

    public JPanel MainMenu() {

        // buttons for menu items
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 3));

        sandwichList.add("Hot Dog");
        sandwichList.add("Gyro");
        sandwichList.add("Italian Beef");
        sandwichList.add("Hamburger");

        //Order item buttons
        JButton jbtGyro;
        p1.add(jbtGyro = new JButton("Gyro"));
        JButton jbtBeef;
        p1.add(jbtBeef = new JButton("Italian Beef"));
        JButton jbtDog;
        p1.add(jbtDog = new JButton("Hot Dog"));
        JButton jbtBurg;
        p1.add(jbtBurg = new JButton("Burger"));
        JButton jbtFrySm;
        p1.add(jbtFrySm = new JButton("Greek Fries(Small)"));
        JButton jbtFryLg;
        p1.add(jbtFryLg = new JButton("Greek Fries(Large)"));
        JButton jbtBev;
        p1.add(jbtBev = new JButton("Beverage"));

        p1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), //panel title
                "Menu Items",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        //individual order
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        JTable table = new JTable(indOrderTable.tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(200, 100));
        p2.add(scrollPane);
        p2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Individual Order",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        //full orderlist
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());

        JTable orderTable = new JTable(subOrderTable.tableModel);

        JScrollPane scrollPane2 = new JScrollPane(orderTable);
        orderTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
        p4.add(scrollPane2);

        p4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), //Table title
                "All Orders on Ticket",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        //Set the transaction button
        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(8, 1));
        JLabel transactionLabel;
        p3.add(transactionLabel = new JLabel("Transactions", SwingConstants.CENTER));
        JButton jbtAdd;
        p3.add(jbtAdd = new JButton("Add to Full Order"));

        JCheckBox studDiscountCheck;
        p3.add(studDiscountCheck = new JCheckBox("Student Discount"));
        JCheckBox seniorDiscountCheck;
        p3.add(seniorDiscountCheck = new JCheckBox("Senior Discount"));
        JCheckBox bdayDiscountCheck;
        p3.add(bdayDiscountCheck = new JCheckBox("Birthday Discount"));
        JButton jbtClear;
        p3.add(jbtClear = new JButton("Clear Order"));

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        p.add(p2, BorderLayout.LINE_START);
        p.add(p1, BorderLayout.CENTER);
        p.add(p3, BorderLayout.LINE_END);
        p.add(p4, BorderLayout.PAGE_END);

        add(p);

        studDiscountCheck.addActionListener(new ListenToStudCheckbox());
        seniorDiscountCheck.addActionListener(new ListenToSenCheckbox());
        bdayDiscountCheck.addActionListener(new ListenToBdayCheckbox());

        jbtGyro.addActionListener(new ListenToGyro());
        jbtBeef.addActionListener(new ListenToBeef());
        jbtDog.addActionListener(new ListenToDog());
        jbtBurg.addActionListener(new ListenToBurg());
        jbtFrySm.addActionListener(new ListenToFrySm());
        jbtFryLg.addActionListener(new ListenToFryLg());
        jbtBev.addActionListener(new ListenToBev());

        jbtAdd.addActionListener(new ListenToAdd());
        jbtClear.addActionListener(new ListenToClear());

        //if you wnat to do something with table clicks you can un comment this code

//        table.getSelectionModel().addListSelectionListener(
//                new ListSelectionListener() {
//                    public void valueChanged(ListSelectionEvent event) {
//                        // do some actions here, for example
//                        // print first column value from selected row
//                        System.out.println("Row selected");
//                        System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
//                        System.out.println(table.getValueAt(table.getSelectedRow(), 1).toString());
//                        System.out.println(table.getValueAt(table.getSelectedRow(), 2).toString());
//                    }
//                });
        return p;
    }

    class ListenToClear implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clearOutOrder();
        }
    }

    class ListenToGyro implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            itemCount++; //add to item count right away
            NumberFormat formatter = NumberFormat.getCurrencyInstance(); //format price to look like dollar amount
            String dollarPrice = formatter.format(5.50);
            updateItemList(itemCount, "Gyro", dollarPrice);

        }
    }

    class ListenToBeef implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            itemCount++; //add to item count right away
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String dollarPrice = formatter.format(4.50);
            updateItemList(itemCount, "Italian Beef", dollarPrice);
        }
    }

    class ListenToDog implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            itemCount++; //add to item count right away
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String dollarPrice = formatter.format(3.50);
            updateItemList(itemCount, "Hot Dog", dollarPrice);
        }
    }

    class ListenToBurg implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            itemCount++; //add to item count right away
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String dollarPrice = formatter.format(3.80);
            updateItemList(itemCount, "Hamburger", dollarPrice);
        }
    }

    class ListenToFrySm implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            itemCount++; //add to item count right away
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String dollarPrice = formatter.format(1.00);
            updateItemList(itemCount, "Small Fry", dollarPrice);
        }
    }

    class ListenToFryLg implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            itemCount++; //add to item count right away
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String dollarPrice = formatter.format(1.50);
            updateItemList(itemCount, "Large Fry", dollarPrice);
        }
    }

    class ListenToBev implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            itemCount++; //add to item count right away
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String dollarPrice = formatter.format(1.00);


            String selectedDrink = drinkOptions();
            //System.out.println("Drink: " + selectedDrink);
            if (selectedDrink != null) {
                updateItemList(itemCount, selectedDrink, dollarPrice);
            }
        }
    }

    class ListenToStudCheckbox implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AbstractButton abstractButton = (AbstractButton) e.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            //System.out.println("Student " + selected);
            if (selected) {
                discount.setStudent(true);
            } else {
                discount.setStudent(false);
            }
        }
    }

    class ListenToSenCheckbox implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AbstractButton abstractButton = (AbstractButton) e.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            //System.out.println("Senior " + selected);
            if (selected) {
                discount.setSenior(true);
            } else {
                discount.setSenior(false);
            }
        }
    }

    class ListenToBdayCheckbox implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AbstractButton abstractButton = (AbstractButton) e.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            //System.out.println("Bday " + selected);
            if (selected) {
                discount.setBirthday(true);
            } else {
                discount.setBirthday(false);
            }
        }
    }

    class ListenToAdd implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //TEMP = Double.parseDouble(jtfResult.getText());

            subOrderName = JOptionPane.showInputDialog("Enter name for order:");
            //System.out.println(subOrderName);

            if (discount.isBirthday) {
                //search order list, is a sandwith is present remove the lowest costing one
                discountList.add("birthday"); // adds to keep track of discount for db
                //System.out.println("SUBTRACT FROM ORDER ------" + bdayDiscount(orderList));
                discountAmount += bdayDiscount(orderList);
            }

            if (discount.isSenior) {
                discountList.add("senior");
                discountAmount += subOrderTotal * 0.2;
            }
            if (discount.isStudent) {
                discountList.add("student");
                discountAmount += subOrderTotal * 0.1;
            }



            PersonalOrder subOrder = new PersonalOrder(orderList, discountList, subOrderName, subOrderTotal, discountAmount);
            /* this was difficult to debug, adding a list for final order only provided reference to that list
            then when cleared the list was cleared in the object, making new Array list objects creates new and does not clear
             */
            finalPersonalOrderList.add(new PersonalOrder(new ArrayList<>(orderList), new ArrayList<>(discountList), subOrderName, subOrderTotal , discountAmount));
            //System.out.println(subOrder);
            updateSuborderList(subOrder.getOrderList(), discountAmount, subOrder.getOrderName(), subOrder.getOrderTotal());

            //clear GUI and variavles after order added
            clearOutOrder();
        }
    }

    private void clearOutOrder() {

        int size = uniqueItemList.size();
        indOrderTable.clearRows(0, size);
        indOrderTable.tableModel.fireTableDataChanged();
            /*
            this resents the checkboxes to not selected, it is a bit sloppy but it works
            will rest all checkboxes on window
            */
        int compCount = mainmenu.getAccessibleContext().getAccessibleChildrenCount();
        for (int j = 0; j < compCount; j++) {
            int childCount = mainmenu.getAccessibleContext().getAccessibleChild(j).getAccessibleContext().getAccessibleChildrenCount();
            for (int i = 0; i < childCount; i++) {
                Component comp = (Component) mainmenu.getAccessibleContext().getAccessibleChild(2).getAccessibleContext().getAccessibleChild(i);
                if (comp instanceof JCheckBox) {
                    //System.out.println(comp.toString());
                    ((JCheckBox) comp).setSelected(false);
                }
            }
        }

        subOrderTotal = 0.0;
        discountAmount = 0.0;
        discount.setBirthday(false);
        discount.setSenior(false);
        discount.setStudent(false);
        discountList.clear();
        orderList.clear();
        uniqueItemList.clear();
    }

    private String drinkOptions() {
        String[] drinkOptionArray = {"Root Beer", "Cola", "Lemon Lime"};
        String selectedValue = (String) JOptionPane.showInputDialog(null, "Select beverage option:", "Drink option...",
                JOptionPane.QUESTION_MESSAGE,
                null,
                drinkOptionArray,
                drinkOptionArray[0]);

        return selectedValue;
    }


    private void updateItemList(int count, String name, String price) {
        Row singleItem = new Row(count, name, price);
        orderList.add(singleItem.getItemName());

        if (!uniqueItemList.contains(singleItem.getItemName())) {
            //indOrderTable.tableModel.data.add
            indOrderTable.tableModel.data.add(singleItem);
            //uniqueItemList.add(singleItem.getItemName());
            uniqueItemList.add(name);
            //System.out.println("Item NOT IN unique list"); //debugging

        } else {
            //System.out.println("Item in unique list"); ///debugging
            //indOrderTable.setValueAt1(singleItem.getItemCount(), 0, 0);
        }

        //finds the frequency of repeated items in list
        Set<String> unique = new HashSet<String>(orderList);
        for (String key : unique) {
            int uniqueCount = Collections.frequency(orderList, key);
            //System.out.println(key + ": " + uniqueCount);
            int indexInOrder = uniqueItemList.indexOf(key);
            //int rowCount = indOrderTable.tableModel.getRowCount();
            indOrderTable.setValueAt1(uniqueCount, indexInOrder, 0);
            indOrderTable.tableModel.fireTableCellUpdated(indexInOrder, 0);
        }

        // needed to update table
        indOrderTable.tableModel.fireTableDataChanged();

        //indOrderTable.setRowUpdate(0, gyroCount, "Gyro", 5.50);
        //System.out.println(count);

        //price string to double
        double doublePrice;
        String strippedDollar = singleItem.getItemPrice().replaceAll("[^\\d.,]", "");
        strippedDollar = strippedDollar.replaceAll("\"", "");
        doublePrice = Double.parseDouble(strippedDollar);

        subOrderTotal += doublePrice;
    }

    private void updateSuborderList(ArrayList<String> items, double discount, String name, Double total) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String dollarRegPrice = formatter.format(total);
        String discountString = formatter.format(discount);
        String finalTotalString = formatter.format(total - discount);

        //.println(items);
        FinalOrder singleRow = new FinalOrder(items.size(), name, dollarRegPrice, discountString, finalTotalString, items);
        subOrderList.add(singleRow);
        subOrderTable.tableModel.data.add(singleRow);
        //System.out.println("In Table " + singleRow.toString());

        // needed to update table
        subOrderTable.tableModel.fireTableDataChanged();
    }

    private double bdayDiscount(ArrayList<String> orderList) {
        for (String wich : sandwichList) {
            boolean found = false;
            for (String orderItem : orderList) {
                if (wich.contains(orderItem)) {
                    //System.out.println("FOUND SANDWICH:" + orderItem);
                    found = true;
                    switch (orderItem) {
                        case "Hot Dog":
                            //System.out.println("substact 3.50");
                            return 3.50;
                        case "Hamburger":
                            //System.out.println("substact 3.80");
                            return 3.80;
                        case "Italian Beef":
                            //System.out.println("substact 4.50");
                            return 4.50;
                        case "Gyro":
                            //System.out.println("substact 5.50");
                            return 5.50;
                    }
                    break;
                }
            }
        }
        return 0.0;
    }
}