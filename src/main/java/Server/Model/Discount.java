package Server.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Discount implements Serializable {

    private String discountID;
    private Date initialDate;
    private Date exposeDate;
    private int discountPercent;
    private int maxAmount;
    private int useCount;
    private ArrayList<Customer> users;

    public Discount(String discountID, Date exposeDate, int discountPercent, int maxAmount, int useCount, ArrayList<Customer> users) {
        this.discountID = discountID;
        this.initialDate = new Date();
        this.exposeDate = exposeDate;
        this.discountPercent = discountPercent;
        this.maxAmount = maxAmount;
        this.useCount = useCount;
        this.users = users;
    }

    public ArrayList<Customer> getUsers() {
        return users;
    }

    public String getDiscountID() {
        return discountID;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
    public String getDiscountPercentString() {
        return String.valueOf(discountPercent);
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public String getMaxAmountString() {
        return String.valueOf(maxAmount);
    }

    public int getUseCount() {
        return useCount;
    }
    public String getUseCountString() {
        return String.valueOf(useCount);
    }

    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

    public Date getInitialDate() {
        return initialDate;
    }
    public String getInitialDateString() {
        return initialDate.toString();
    }

    public Date getExposeDate() {
        return exposeDate;
    }
    public String getExposeDateString() {
        return exposeDate.toString();
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountID='" + discountID + '\'' +
                ", initialDate=" + initialDate +
                ", exposeDate=" + exposeDate +
                ", discountPercent=" + discountPercent +
                ", maxAmount=" + maxAmount +
                ", useCount=" + useCount +
                '}';
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public void setExposeDate(Date exposeDate) {
        this.exposeDate = exposeDate;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }
}
