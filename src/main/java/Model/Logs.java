package Model;

import java.util.Date;

public class Logs {
    protected String logID;
    protected Date date;
    protected float pricePaid;

    public Logs(String logID, Date date, float pricePaid) {
        this.logID = logID;
        this.date = date;
        this.pricePaid = pricePaid;
    }


    public String getLogID() {
        return logID;
    }
}
