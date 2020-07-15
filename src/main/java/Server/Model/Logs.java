package Server.Model;

import java.io.Serializable;
import java.util.Date;

public class Logs implements Serializable {
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

    public Date getDate() {
        return date;
    }

    public float getPricePaid() {
        return pricePaid;
    }
}
