package Model;

import java.util.ArrayList;
import java.util.Date;

public class Off {
    private String offID;
    private ArrayList<Good> goodsForOff;
    private String offStatus;
    private Date initialDate;
    private Date exposeDate;
    private int discountPercent;

    public Off(String offID, ArrayList<Good> goodsForOff, String offStatus, Date initialDate, Date exposeDate, int discountPercent) {
        this.offID = offID;
        this.goodsForOff = goodsForOff;
        this.offStatus = offStatus;
        this.initialDate = initialDate;
        this.exposeDate = exposeDate;
        this.discountPercent = discountPercent;
    }
}
