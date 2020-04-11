package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Good {
    private String goodID;
    private String goodStatus;
    private HashMap<String,Integer > sellerAndPrices =new HashMap<String, Integer>();
    private String companyName;
    private String stockStatus;
    private Category category;
    private String explanation;
    private float point;
    private ArrayList<Comment> allComments;

    public Good(String goodID, String goodStatus, String companyName, String stockStatus, Category category, String explanation) {
        this.goodID = goodID;
        this.goodStatus = goodStatus;
        this.companyName = companyName;
        this.stockStatus = stockStatus;
        this.category = category;
        this.explanation = explanation;
    }

    public void setSellerAndPrices(HashMap<String, Integer> sellerAndPrices) {
        this.sellerAndPrices = sellerAndPrices;
    }
}
