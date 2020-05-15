package Model;

import jdk.tools.jlink.internal.DirArchive;

import java.util.ArrayList;
import java.util.Date;

public class Off {
    private String offID;
    private ArrayList<Good> goodsForOff;
    private OffStatus offStatus;
    private enum OffStatus {creating,editing,confirmed};
    private Date initialDate;
    private Date exposeDate;
    private int discountPercent;

    public void editInformation(ArrayList<Good> goods, Date exposeDate, Date initialDate, int discountPercent) {
        this.goodsForOff = goods;
        this.exposeDate = exposeDate;
        this.discountPercent = discountPercent;
        this.initialDate = initialDate;
    }

    public Off(String offID, ArrayList<Good> goodsForOff, Date exposeDate, Date initialDate, int discountPercent) {
        this.offID = offID;
        this.goodsForOff = goodsForOff;
        this.offStatus = OffStatus.confirmed;
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
