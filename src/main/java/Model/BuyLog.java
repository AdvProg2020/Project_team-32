package Model;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Logs {
    private String discountMade;
    private ArrayList<Good> goodsBought;
    private String sellerUserName;
    private String deliveryStatus;

    public BuyLog(String logID, Date date, int pricePaid, String discountMade, ArrayList<Good> goodsBought, String sellerUserName, String deliveryStatus) {
        super(logID, date, pricePaid);
        this.discountMade = discountMade;
        this.goodsBought = goodsBought;
        this.sellerUserName = sellerUserName;
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "BuyLog{" +
                "discountMade='" + discountMade + '\'' +
                ", goodsBought=" + goodsBought +
                ", sellerUserName='" + sellerUserName + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}
