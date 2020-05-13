package Model;

import java.util.ArrayList;
import java.util.Date;

public class Off {
    private String offID;
    private ArrayList<Good> goodsForOff;
    private OffStatus offStatus;
    private enum OffStatus { creating,editing,confirmed};
    private Date initialDate;
    private Date exposeDate;
    private int discountPercent;

    public Off(String offID, ArrayList<Good> goodsForOff, OffStatus offStatusValue, Date initialDate, Date exposeDate, int discountPercent) {
        this.offID = offID;
        this.goodsForOff = goodsForOff;
        this.offStatus = offStatusValue;
        this.initialDate = initialDate;
        this.exposeDate = exposeDate;
        this.discountPercent = discountPercent;
    }

    public String getOffID() {
        return offID;
    }

    public ArrayList<Good> getGoodsForOff() {
        return goodsForOff;
    }

    public String getOffStatus() {
        return OffStatus;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public Date getExposeDate() {
        return exposeDate;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public String toString() {
        String output = "Off{" +
                "offID='" + offID + '\'' +
                ", offStatus='" + OffStatus + '\'' +
                ", initialDate=" + initialDate +
                ", exposeDate=" + exposeDate +
                ", discountPercent=" + discountPercent
                +'}';
        for (Good good : this.goodsForOff) {
            output+= good.getName()+"  "+ good.getGoodID()+"\n";
        }
        return output;
    }
}
