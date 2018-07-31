This is the final project to ITM 311 for the Summer 2018 semester.
The project was to create a usable GUI for an ordering system for Georges Gyros.


Logon

Login used the special Swing object for password fields JPasswordField
The login then checks if the username and pw are correct
FOR SAMPLE username and pw are both hardcoded to be:   root

![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/image1.png)

![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/image2.png)

![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/image3.png)

![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/imag4.png)

Choice of Sandwich, Potatos, Beverage

-Menu control was coded to use the menu item buttons to add to order

![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/image5.png)

Each item is added to a individual order menu on the left side of the screen. This uses the Jtable and Jtable model objects. If multiple items are added to the order count dynamically.

![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/image6.png)

Once the single order is complete chose the discounts to be applied and click the button to add to full order needs to be clicked. This brings up a dialog to ender the name for the order
    • The discounts are checks boxes because a senior can be a student or have a birthday, they are not mutually exclusive
    • The Birthday discount parses the order list to see if a sandwich was ordered, it there is a sandwich in the list it subtracts the amount of one from the total cost. If there are multiple it subtracts the cost of the cheapest sandwich in the list.

![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/image6.png)
![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/image7.png)

	
    • Once complete with all orders the pay button is pressed. This shows the totals for each sub-order and the final bill.
    • If you click on and row in the order table a message with the items order with be displayed in a dialogue window.

Total Cost per Customer/ Total Cost if Multiple Orders

    • Once the pay button is clicked a selection for the payment method is shown. When once is selected the receipt is output in the console. 
*** In completing this report a bug was found where the tax was calculated after discounts applies (thanks to the excel check, it has since been fixed in raw code, if any screen captures show error tax calculations rest assured the bug is fixed in the program. ***

![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/image8.png)
![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/image9.png)

Reciept output from console.


![alt text](https://github.com/ttruty/GeorgesGUI/blob/master/images/image10.png)


				References


[1] Farrell, J. (2016). Java programming (8th ed.). Australia: Cengage Learning. 

[2] A Visual Guide to Layout Managers (n.d) In Java Docs. Retrieved July 20, 2018, from 
https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
[3] How to Use Card Layout (n.d) In Java Docs. Retrieved July 20, 2018, from 
https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html

[4] How to Use Tables (n.d) In Java Docs. Retrieved July 20, 2018, from 
https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#eg

[5] CardLayout (swing) with action listeners on buttons (Apr 23, 2013) Stackoverflow Question. Retrieved July 22, 2018, from 
https://stackoverflow.com/questions/16179764/cardlayout-swing-with-action-listeners-on-buttons

[6] How do I use CardLayout(Jul 4, 2013) Stackoverflow Question.. Retrieved July 20, 2018, from 
https://stackoverflow.com/questions/17477891/how-do-i-use-cardlayout-for-my-java-program-for-login-and-menu-items

[7] JTable – Selected Row click event (Apr 12, 2012) Stackoverflow Question. Retrieved July 22, 2018, from 
https://stackoverflow.com/questions/10128064/jtable-selected-row-click-event		

Appendix

Extra flair in program.
If the birthday discount is applied it says “Happy Birthday” on receipt.
				
