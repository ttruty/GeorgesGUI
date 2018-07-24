import java.util.ArrayList;

public class PersonalOrder{
    @Override
    public String toString() {
        return "PersonalOrder{" +
                "orderList=" + orderList +
                ", discounts=" + discounts +
                ", orderName='" + orderName + '\'' +
                ", orderTotal=" + orderTotal +
                ", discountAmount=" + discountAmount +
                '}';
    }

    private ArrayList<String> orderList;
    private ArrayList<String> discounts;
    private String orderName;
    private double orderTotal;
    private double discountAmount;


    public PersonalOrder(ArrayList<String> orderList, ArrayList<String> discounts, String orderName, double orderTotal, double discountAmount) {
        this.orderList = orderList;
        this.discounts = discounts;
        this.orderName = orderName;
        this.orderTotal = orderTotal;
        this.discountAmount = discountAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public ArrayList<String> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(ArrayList<String> discounts) {
        this.discounts = discounts;
    }

    public ArrayList<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<String> orderList) {
        this.orderList = orderList;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
}