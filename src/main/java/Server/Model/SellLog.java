package Server.Model;

import Server.Database.Database;
import Server.Database.DatabaseType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SellLog extends Logs {
    private float amountReducedForOff;
    private Good soldGood;
    private String buyerUserNmae;
    private String postStatus;

    public SellLog(String logID, Date date, float pricePaid, float amountReducedForOff, Good soldGood, String buyerUserName, String postStatus) {
        super(logID, date, pricePaid);
        this.amountReducedForOff = amountReducedForOff;
        this.soldGood = soldGood;
        this.buyerUserNmae = buyerUserName;
        this.postStatus = postStatus;
        store();
    }

    public String getBuyerUserNmae() {
        return buyerUserNmae;
    }

    public Good getSoldGood() {
        return soldGood;
    }

    @Override
    public String toString() {
        return "SellLog{" +
                "amountReducedForOff=" + amountReducedForOff +
                ", soldGood=" + soldGood +
                ", buyerUserNmae='" + buyerUserNmae + '\'' +
                ", postStatus='" + postStatus + '\'' +
                ", logID='" + logID + '\'' +
                ", date=" + date +
                ", pricePaid=" + pricePaid +
                '}';
    }

    @Override
    public void store() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-mm-dd hh:mm:ss");
        String s = "INSERT INTO sellLogs " +
                "VALUES ('" + logID + "', '" +
                simpleDateFormat.format(date) + "', " +
                pricePaid + ", " + amountReducedForOff + ", '" +
                soldGood.getGoodID() + "', '" + buyerUserNmae + "', '" +
                postStatus + "');";
        Database.getInstance(DatabaseType.logsDatabase).update(s);
    }

    @Override
    public void update() {

    }
}
