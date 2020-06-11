package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Logs {
    private float discountMade;
    private Good goodsBought;
    private String sellerUserName;
    private String deliveryStatus;

    public BuyLog(String logID, Date date, float pricePaid, float discountMade, Good goodsBought, String sellerUserName, String deliveryStatus) {
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

    public float getDiscountMade() {
        return discountMade;
    }

    public Good getGoodsBought() {
        return goodsBought;
    }

    public String getSellerUserName() {
        return sellerUserName;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }
}
