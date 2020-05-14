package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Discount {
    private String discountID;
    private Date initialDate;
    private Date exposeDate;
    private int discountPercent;
    private int maxAmount;
    private int useCount;

    public Discount(String discountID, Date exposeDate, int discountPercent, int maxAmount) {
        this.discountID = discountID;
        this.initialDate = new Date();
        this.exposeDate = exposeDate;
        this.discountPercent = discountPercent;
        this.maxAmount = maxAmount;
        this.useCount = 0;
    }

    public String getDiscountID() {
        return discountID;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getUseCount() {
        return useCount;
    }

    public void setUseCount(int useCount) {
        this.useCount = useCount;
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
