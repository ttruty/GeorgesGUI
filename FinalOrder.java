import java.util.ArrayList;

public class FinalOrder {

    private int itemCount = 0;
    private String itemName = "";
    private String regCost = "";
    private String discountAmount ="";
    private String finalTotal = "";
    private ArrayList<String> order;

    public FinalOrder(int itemCount, String itemName, String regCost, String discountAmount, String finalTotal, ArrayList<String> order) {
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.regCost = regCost;
        this.discountAmount = discountAmount;
        this.finalTotal = finalTotal;
        this.order = order;
    }

    public ArrayList<String> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<String> order) {
        this.order = order;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRegCost() {
        return regCost;
    }

    public void setRegCost(String regCost) {
        this.regCost = regCost;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(String finalTotal) {
        this.finalTotal = finalTotal;
    }
}