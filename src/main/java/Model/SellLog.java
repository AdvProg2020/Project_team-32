package Model;

import java.util.Date;

public class SellLog extends Logs {
    private int amountReducedForOff;
    private Good soldGood;
    private String buyerUserNmae;
    private String postStatus;

    public SellLog(String logID, Date date, int pricePaid, int amountReducedForOff, Good soldGood, String buyerUserNmae, String postStatus) {
        super(logID, date, pricePaid);
        this.amountReducedForOff = amountReducedForOff;
        this.soldGood = soldGood;
        this.buyerUserNmae = buyerUserNmae;
        this.postStatus = postStatus;
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
}
