package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Good {
    private static ArrayList<Good> allGoods;
    private String name;
    private String goodID;
    private String goodStatus;
    private HashMap<String, Integer> sellerAndPrices = new HashMap<String, Integer>();
    private String companyName;
    private String stockStatus;
    private Category category;
    private String explanation;
    private float point;
    private ArrayList<Comment> allComments;

    public Good(String name, String goodID, String goodStatus, String companyName, String stockStatus, Category category, String explanation) {
        this.name = name;
        this.goodID = goodID;
        this.goodStatus = goodStatus;
        this.companyName = companyName;
        this.stockStatus = stockStatus;
        this.category = category;
        this.explanation = explanation;
    }
//
//    public Good(String goodID, String goodStatus, String companyName, String stockStatus, Category category, String explanation) {
//        this.goodID = goodID;
//        this.goodStatus = goodStatus;
//        this.companyName = companyName;
//        this.stockStatus = stockStatus;
//        this.category = category;
//        this.explanation = explanation;
//    }

    public static String compare(Good firstGood, Good secondGood) {

    }

    public void setSellerAndPrices(HashMap<String, Integer> sellerAndPrices) {
        this.sellerAndPrices = sellerAndPrices;
    }

    private void managePoint(int point) {

    }

    public static ArrayList<Good> selectWithCategory(ArrayList<Good> allGoods, HashMap<String, String> filtersAndValues) {

    }

    private void editInformation(String goodStatus, String companyName, String stockStatus, Category category, String explanation) {

    }

    public void addComment(String comment, Person person) {

    }

    public String getName() {
        return name;
    }

    public String getGoodID() {
        return goodID;
    }

    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", goodID='" + goodID + '\'' +
                ", goodStatus='" + goodStatus + '\'' +
                ", stockStatus='" + stockStatus + '\'' +
                ", category=" + category +
                ", explanation='" + explanation + '\'' +
                ", point=" + point +
                ", allComments=" + allComments +
                '}';
    }
}
