package Model;

import java.util.Date;

public class Logs {
    private String logID;
    private Date date;
    private int pricePaid;

    public Logs(String logID, Date date, int pricePaid) {
        this.logID = logID;
        this.date = date;
        this.pricePaid = pricePaid;
    }
}
