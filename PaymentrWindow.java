import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.*;

public class PaymentrWindow extends JPanel {

    private String display = "";
    private JButton button;
    private JPanel mainPanel;
    private Component mainmenu;
    ArrayList<PersonalOrder> orders  = MainOrderWindow.finalPersonalOrderList;

    private static TableFullOrder subOrderTable = MainOrderWindow.subOrderTable;

    public PaymentrWindow(JPanel mainPanel) {
        // main panel initialize and add
        this.mainPanel = mainPanel;
        setBackground(Color.ORANGE);
        mainmenu = add(PayMenu());
        //these are the buttons to advance cards
        add(returnButton());
        add(newButton());
    }

    private JButton returnButton() {
        button = new JButton("Add to Order");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu_V1 main = new MainMenu_V1();
                main.switchPanel(mainPanel, "MENU");
            }
        });

        return button;
    }

    private JButton newButton() {
        button = new JButton("New Order");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orders.clear();
                subOrderTable.tableModel.clearRows(0, subOrderTable.table.getRowCount());
                MainMenu_V1 main = new MainMenu_V1();
                main.revalidate();
                main.switchPanel(mainPanel, "MENU");
                int rowCount = subOrderTable.table.getRowCount();

                //clear table and repain menu
                subOrderTable.clearRows(0, rowCount);
            }
        });
        return button;
    }

    public JPanel PayMenu() {
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

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(3, 2));

        JLabel subLabel;
        subLabel = new JLabel("Sub-total  =   ");
        subLabel.setFont(new Font("Serif", Font.BOLD, 18));
        subLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        double subTotal = getTotalAmount();
        String stringSub = getStringTotal(subTotal);

        JLabel subAmountLbl;
        subAmountLbl = new JLabel(stringSub);
        subAmountLbl.setFont(new Font("Serif", Font.BOLD, 18));


        JLabel taxLabel;
        taxLabel = new JLabel("Cook county tax 10%   =   ");
        taxLabel.setFont(new Font("Serif", Font.BOLD, 18));
        taxLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        double taxTotal = getTotalAmount() * 0.10;
        String stringTax = getStringTotal(taxTotal);

        JLabel taxAmountLbl;
        taxAmountLbl = new JLabel(stringTax);
        taxAmountLbl.setFont(new Font("Serif", Font.BOLD, 18));

        JLabel totalLabel;
        totalLabel = new JLabel("Total for Order   =  ");
        totalLabel.setFont(new Font("Serif", Font.BOLD, 32));

        double totalOrderTotal = getTotalAmount() + taxTotal;
        String stringTotal = getStringTotal(totalOrderTotal);

        JLabel totalAmountLbl;
        totalAmountLbl = new JLabel(stringTotal);
        totalAmountLbl.setFont(new Font("Serif", Font.BOLD, 32));

        p2.add(totalLabel);
        p2.add(totalAmountLbl);
        p2.add(subLabel);
        p2.add(subAmountLbl);
        p2.add(taxLabel);
        p2.add(taxAmountLbl);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1));
        JButton payButton;
        p3.add(payButton = new JButton("Pay"));


        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setPreferredSize(new Dimension(600, 250));
        //p.add(p2, BorderLayout.LINE_START);
        //p.add(p1, BorderLayout.CENTER);
        p.add(p4, BorderLayout.CENTER);
        p.add(p2, BorderLayout.NORTH);
        p.add(p3, BorderLayout.PAGE_END);

        add(p);

        payButton.addActionListener(new ListenToPay());

        orderTable.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        // do some actions here, for example
                        // print first column value from selected row
                        if(!event.getValueIsAdjusting()) { // this stops from firing both at mousedown and up events
                            //System.out.println("Row selected");
                            //System.out.println(orderTable.getSelectedRow());
                            //System.out.println(orders.get(orderTable.getSelectedRow()).toString());
                            try {
                                StringBuilder itemsMessage = new StringBuilder("Items: \n");
                                for (String order : orders.get(orderTable.getSelectedRow()).getOrderList()) {
                                    itemsMessage.append(order + "\n");
                                }
                                //String orderListMessage = orders.get(orderTable.getSelectedRow()).getOrderList().toString();
                                JOptionPane.showMessageDialog(null, itemsMessage);
                            }
                            catch (ArrayIndexOutOfBoundsException e) {

                            }

                        }

                    }
                });

        return p;
    }

    class ListenToPay implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String dollarPrice = formatter.format(1.00);


            String selectedPay = payMethods();
            //System.out.println("Drink: " + selectedDrink);
            if (selectedPay != null) {
               //System.out.println(selectedPay);
            }
        }
    }

    private void showRowItems(int i){
        //System.out.println(orders.get(i).getOrderList().toString());
    }

    private String payMethods() {
        String[] payOptionArray = {"Cash", "Card", "PayPal", "Apple/Google pay", "Bitcoind"};
        String selectedValue = (String) JOptionPane.showInputDialog(null, "Select payment method:", "Payment option...",
                JOptionPane.QUESTION_MESSAGE,
                null,
                payOptionArray,
                payOptionArray[0]);
        //TODO functions for each pay methods

        StringBuilder receipt = printRec(selectedValue);
        System.out.println(receipt);
        return selectedValue;
    }

    private double getTotalAmount (){
        double sumTotal = 0;
        double discountTotal = 0;
        for (int i =  0; i < orders.size(); i++){

            //System.out.println(orders.get(i).toString());
            //System.out.println(orders.get(i).getOrderTotal());

            sumTotal += orders.get(i).getOrderTotal();
            discountTotal += orders.get(i).getDiscountAmount();

        }

        return sumTotal - discountTotal;
    }

    private String getStringTotal (double total) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(); //format price to look like dollar amount
        String dollarSumTotal = formatter.format(total);
        return dollarSumTotal;
    }

    public StringBuilder printRec(String payMethod) {

        double allDiscount = 0.0;
        boolean bday = false;
        Date date = new Date();

        StringBuilder receipt = new StringBuilder("RECEIPT: \n");

        receipt.append("[ WELCOME TO GEORGES! ]" +  "\n");
        receipt.append("----------------------" +  "\n");
        receipt.append("Today's Date is: " + date + "\n");
        receipt.append("Orders:" + "\n" );
        for (PersonalOrder pOrder : orders)
        {
            receipt.append("Order for: " + pOrder.getOrderName() + "\n" );
            receipt.append("Items: ");
            for (String item : pOrder.getOrderList()){
                receipt.append(item + ", ");
            }
            receipt.append("\n");
            receipt.append("Discounts: " );
            for (String discount : pOrder.getDiscounts()){
                if (discount.equals("birthday")){
                    bday = true;
                }
                receipt.append(discount + " ");
            }
            receipt.append("\n");

            double subsubTotal = pOrder.getOrderTotal();
            double discoAmount = pOrder.getDiscountAmount();
            allDiscount += discoAmount;
            double sumTotal = subsubTotal - discoAmount;


            receipt.append("Subtotal for single order:   " + getStringTotal(subsubTotal) + "\n" );
            receipt.append("Discounts for single order:  " + getStringTotal(discoAmount) +  "\n" );
            receipt.append("Order Total:                 " + getStringTotal(sumTotal)  + "\n" );
        }

        double subTotal = getTotalAmount();
        String stringSub = getStringTotal(subTotal);

        double taxTotal = getTotalAmount() * 0.10;
        String stringTax = getStringTotal(taxTotal);

        double totalOrderTotal = getTotalAmount() + taxTotal;
        String stringTotal = getStringTotal(totalOrderTotal);

        receipt.append("==========================\n");
        receipt.append("Total Subtotal:        " + stringSub + "\n" );
        receipt.append("Total Discounts:       " + getStringTotal(allDiscount) +  "\n" );
        receipt.append("Cook County Tax -10%:  " + stringTax  + "\n" );
        receipt.append("Final Total:           " + stringTotal  + "\n" );
        receipt.append("Payment Method:        " + payMethod+ "\n");

        receipt.append("---------------------------"+ "\n");
        receipt.append("THANK YOU FOR EATING AT GEORGES!"+ "\n");
        // a little extra flair
        if (bday){
            receipt.append("AND WE HOPE YOU HAVE A GREAT BIRTHDAY!");
        }
        return receipt;
    }
}