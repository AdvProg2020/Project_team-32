package Model;

//import jdk.tools.jlink.internal.DirArchive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Off implements Serializable {
    private String offID;
    private ArrayList<Good> goodsForOff;
    private OffStatus offStatus;

    private static ArrayList<Off> allOffs = new ArrayList<>();
    private enum OffStatus implements Serializable {creating, editing, confirmed}
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

    public String getInitialDateString() {
        return initialDate.toString();
    }

    public Date getExposeDate() {
        return exposeDate;
    }

    public String getExposeDateString() {
        return exposeDate.toString();
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public String getDiscountPercentString() {
        return String.valueOf(discountPercent);
    }

    @Override
    public String toString() {
        String output = "Off{" +
                "offID='" + offID + '\'' +
                ", initialDate=" + initialDate +
                ", exposeDate=" + exposeDate +
                ", discountPercent=" + discountPercent
                + '}';
        for (Good good : this.goodsForOff) {
            output += good.getName() + "  " + good.getGoodID() + "\n";
        }
        return output;
    }

    public static void addOff(Off off) {
        allOffs.add(off);
    }

    public static ArrayList<Off> getAllOffs() {
        return new ArrayList<>(allOffs);
    }
}
