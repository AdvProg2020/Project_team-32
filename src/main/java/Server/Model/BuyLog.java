package Server.Model;

import Server.Database.Database;
import Server.Database.DatabaseType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyLog extends Logs {

    private float discountMade;
    private Good goodsBought;
    private String sellerUserName;
    private String address ;
    private String phoneNumber;
    public enum DeliveryStatus {SENT,WATING};
    private DeliveryStatus deliveryStatus;

    public BuyLog(String logID, Date date, float pricePaid, float discountMade, Good goodsBought, String sellerUserName, DeliveryStatus deliveryStatus, String address, String phoneNumber) {
        super(logID, date, pricePaid);
        this.discountMade = discountMade;
        this.goodsBought = goodsBought;
        this.sellerUserName = sellerUserName;
        this.address=address;
        this.phoneNumber =phoneNumber;
        this.deliveryStatus = deliveryStatus;
        this.store();
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

    public Good getGoodsBought() {
        return goodsBought;
    }

    public String getSellerUserName() {
        return sellerUserName;
    }

    @Override
    public synchronized void store() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-mm-dd hh:mm:ss");
        String s = "INSERT INTO buyLogs " +
                "VALUES ('" + logID + "', '" +
                simpleDateFormat.format(date) + "', " +
                pricePaid + ", " + discountMade + ", '" +
                goodsBought.getGoodID() + "', '" + sellerUserName + "', '" +
                deliveryStatus + "');";
        Database.getInstance(DatabaseType.logsDatabase).update(s);
    }

    @Override
    public synchronized void update() {


    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
