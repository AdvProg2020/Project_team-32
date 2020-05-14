package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Good {
    public static ArrayList<Good> allGoods;
    private String name;
    private String goodID;
    private String goodStatus;
    private HashMap<String, Integer> sellerAndPrices = new HashMap<String, Integer>();
    private ArrayList<Seller> sellers;
    private String companyName;
    private String stockStatus;
    private Category category;
    private String explanation;
    //private float point;
    private int point;

    //------------------------------------------------------------
    private int numberOfViews; //this should set after any view

    public int getNumberOfViews() {
        return numberOfViews;
    }
    //------------------------------------------------------------

    //------------------------------------------------------------


    private ArrayList<Comment> allComments;

    private HashMap<String , String> properties;

    public Good(String name, String goodID, String goodStatus, String companyName, String stockStatus, Category category
            , String explanation, HashMap<String , String > properties) {
        this.name = name;
        this.goodID = goodID;
        this.goodStatus = goodStatus;
        this.companyName = companyName;
        this.stockStatus = stockStatus;
        this.category = category;
        this.explanation = explanation;
        sellers = new ArrayList<Seller>();

        this.properties = properties;
    }

    public HashMap<String, Integer> getSellerAndPrices() {
        return sellerAndPrices;
    }

    public Category getCategory() {
        return category;
    }

    public static Good getGoodById(String Id){
        for (Good good : allGoods) {
            if(good.goodID.equals(Id)){
                return good;
            }
        }
        return null;
    }

    public ArrayList<Seller> getSellers(){
        return sellers;
    }

    public static String compare(Good firstGood, Good secondGood) {

    }

    public void setSellerAndPrices(HashMap<String, Integer> sellerAndPrices) {
        this.sellerAndPrices = sellerAndPrices;
    }

    /*private void managePoint(int point) {

    }*/

    public static ArrayList<Good> selectWithCategory(ArrayList<Good> allGoods, HashMap<String, String> filtersAndValues) {

    }

    public void setPoint(int point) {
        this.point = point;
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

    public void removeSeller(Seller person) {
        sellerAndPrices.remove(person.getUserName());
        sellers.remove(person);
    }

    public boolean hasProperty(String property, String value){
        if(properties.containsKey(property)){
            if(properties.get(property).equals(value)){
                return true;
            }
        }

        return false;

    }

    public int getPoint() {
        return point;
    }
}
