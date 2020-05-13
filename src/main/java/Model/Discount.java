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
}
