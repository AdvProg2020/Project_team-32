package Model;

import java.util.Date;

public class Logs {
    protected String logID;
    protected Date date;
    protected int pricePaid;

    public Logs(String logID, Date date, int pricePaid) {
        this.logID = logID;
        this.date = date;
        this.pricePaid = pricePaid;
    }


    public String getLogID() {
        return logID;
    }
}
