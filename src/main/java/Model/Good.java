package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Good {
    public static ArrayList<Good> confirmedGoods = new ArrayList<>();
    private static ArrayList<Good> allGoods = new ArrayList<>();
    private String name;
    private String goodID;
    private Status goodStatus;

    private enum Status {MAKE_REQUEST,EDIT_REQUEST,CONFIRMED}

    private HashMap<String, Integer> sellerAndPrices = new HashMap<String, Integer>();
    private ArrayList<Seller> sellers;
    private String companyName;
    private Category category;
    private String explanation;
    private float point;
    private HashMap<String , String> properties;

    //------------------------------------------------------------
    private int numberOfViews; //this should set after any view

    public int getNumberOfViews() {
        return numberOfViews;
    }
    //------------------------------------------------------------

    //------------------------------------------------------------


    public void setPoint(float point) {
        this.point = point;
    }

    private ArrayList<Comment> allComments;



    public Good(String name, String goodID, Seller seller, String companyName, Category category
            , String explanation, HashMap<String , String > properties,int price) {
        this.name = name;
        this.goodID = goodID;
        this.goodStatus = Status.MAKE_REQUEST;
        this.companyName = companyName;
        this.category = category;
        this.explanation = explanation;
        this.properties = properties;
        this.sellerAndPrices = new HashMap<>();
        this.sellerAndPrices.put(seller.getUserName(), price);
        this.sellers = new ArrayList<Seller>();
        this.sellers.add(seller);
        allGoods.add(this);
    }

    public void editInfo(Good editGood, Seller seller) {
        this.name = editGood.name;
        this.goodID = editGood.goodID;
        this.companyName = editGood.companyName;
        this.category = editGood.category;
        this.explanation = editGood.explanation;
        this.properties = editGood.properties;
        this.sellerAndPrices.replace(seller.getUserName(),editGood.sellerAndPrices.get(seller.getUserName()));
        this.confirmStatus();
    }

    public HashMap<String, Integer> getSellerAndPrices() {
        return sellerAndPrices;
    }

    public Category getCategory() {
        return category;
    }

    public static Good getGoodFromAllGoods(String goodID){
        for (Good good : allGoods) {
            if(good.goodID.equals(goodID)){
                return good;
            }
        }
        return null;
    }

    public static Good getGoodById(String Id){
        for (Good good : confirmedGoods) {
            if(good.goodID.equals(Id)){
                return good;
            }
        }
        return null;
    }

    public ArrayList<Seller> getSellers(){
        return sellers;
    }

    public void addSellerAndPrice(String sellerUserName, int price) {
        this.sellerAndPrices.put(sellerUserName,price);
    }

    public void confirmStatus() {
        goodStatus = Status.CONFIRMED;
    }

    public void editingStatus(){
        goodStatus = Status.EDIT_REQUEST;
    }

    /*private void managePoint(int point) {

    }*/


    private void editInformation(String goodStatus, String companyName, String stockStatus, Category category, String explanation) {

    }

    public void addComment(String userName, Good good, String commentString, String commentStatus, String title){
        Comment comment = new Comment(userName, good, commentString, commentStatus, title);
        allComments.add(comment);
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

    public float getPoint() {
        return point;
    }

    public boolean hasSeller(String seller){
        if(sellerAndPrices.containsKey(seller)){
            return true;
        }
        return false;
    }

    public String getProperty(String property){
        return properties.get(property);
    }

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }
}
