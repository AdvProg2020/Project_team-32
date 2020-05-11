package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Discount {
    private String discountID;
    private Date initialDate;
    private Date exposeDate;
    private String discountPercentAndMaxValue;
    private int useCount;



    // ali sharifi
    @Override
    public String toString() {
        return "Discount{" +
                "discountID='" + discountID + '\'' +
                ", initialDate=" + initialDate +
                ", exposeDate=" + exposeDate +
                ", discountPercentAndMaxValue='" + discountPercentAndMaxValue + '\'' +
                ", useCount=" + useCount +
                '}';
    }
}
