package Server.Model;

import Server.Database.Database;
import Server.Database.DatabaseType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Good implements Serializable, Storable {
    public static ArrayList<Good> confirmedGoods = new ArrayList<>();
    private static ArrayList<Good> allGoods = new ArrayList<>();
    private String name;
    private String goodID;
    private Status goodStatus;



    private enum Status implements Serializable {MAKE_REQUEST, EDIT_REQUEST, CONFIRMED}
    @SerializedName("sellerAndPrices")
    @Expose
    private HashMap<String, Integer> sellerAndPrices = new HashMap<String, Integer>();
    @SerializedName("sellers")
    @Expose
    private ArrayList<Seller> sellers;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("explanation")
    @Expose
    private String explanation;
    @SerializedName("point")
    @Expose
    private float point;
    @SerializedName("properties")
    @Expose
    private HashMap<String, String> properties;
    @SerializedName("imageAddress")
    @Expose
    private String imageAddress;
    @SerializedName("allComments")
    @Expose
    private ArrayList<Comment> allComments = new ArrayList<>();

    @SerializedName("numberOfRates")
    @Expose
    private int numberOfRates;

    @SerializedName("bumberOfViews")
    @Expose
    private int numberOfViews; //this should set after any view

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public void setPoint(float point) {

        this.point = (this.point) * numberOfRates + point;
        this.numberOfRates += 1;
        this.point /= this.numberOfRates;

    }


    public Good(String name, String goodID, Seller seller, String companyName, Category category
            , String explanation, HashMap<String, String> properties, int price) {
        this.name = name;
        this.goodID = goodID;
        this.goodStatus = Status.MAKE_REQUEST;
        this.companyName = companyName;
        this.category = category;
        this.explanation = explanation;
        this.sellerAndPrices = new HashMap<>();
        this.sellerAndPrices.put(seller.getUserName(), price);
        this.sellers = new ArrayList<Seller>();
        this.sellers.add(seller);
        allGoods.add(this);
        this.properties = properties != null ? new HashMap<>(properties) : new HashMap<>();
        this.properties.put("company name", companyName);
        this.properties.put("name", name);
        // TODO ali sharifi
        store();
    }

    @Override
    public void store() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        /*
        String s = "INSERT INTO good " +
                "VALUES ('" + name + "', '" + goodID + "', '" +
                gson.toJson(goodStatus) + "', '" + companyName + "', '" + explanation +
                "', '" + gson.toJson(sellerAndPrices) + "', '" + gson.toJson(sellers) + "', '" +
                gson.toJson(properties) +  "');";

         */
        String s = "INSERT INTO goods " +
                "VALUES ('" + gson.toJson(this, Good.class) + "');";
        Database.getInstance(DatabaseType.goodsDatabase).update(s);
    }


    public Good(String name, String goodID, String companyName, String explanation, float point, String imageAddress, int numberOfRates, int numberOfViews) {
        this.name = name;
        this.goodID = goodID;
        this.companyName = companyName;
        this.explanation = explanation;
        this.point = point;
        this.imageAddress = imageAddress;
        this.numberOfRates = numberOfRates;
        this.numberOfViews = numberOfViews;
    }



    @Override
    public void update() {

    }

    public void editInfo(Good editGood, Seller seller) {
        this.name = editGood.name;
        this.companyName = editGood.companyName;
        this.category = editGood.category;
        this.explanation = editGood.explanation;
        this.properties = editGood.properties;
        this.sellerAndPrices.replace(seller.getUserName(), editGood.sellerAndPrices.get(seller.getUserName()));
        this.confirmStatus();
    }


    public HashMap<String, String> getProperties() {
        return properties;
    }

    public HashMap<String, Integer> getSellerAndPrices() {
        return sellerAndPrices;
    }

    public Category getCategory() {
        return category;
    }

    public static Good getGoodFromAllGoods(String goodID) {
        for (Good good : allGoods) {
            if (good.goodID.equals(goodID)) {
                return good;
            }
        }
        return null;
    }

    public static Good getGoodById(String Id) {
        for (Good good : confirmedGoods) {
            if (good.goodID.equals(Id)) {
                return good;
            }
        }
        return null;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void addSellerAndPrice(String sellerUserName, int price) {
        this.sellerAndPrices.put(sellerUserName, price);
    }

    public void confirmStatus() {
        confirmedGoods.add(this);
        goodStatus = Status.CONFIRMED;
    }

    public void editingStatus() {
        goodStatus = Status.EDIT_REQUEST;
        confirmedGoods.remove(this);
    }

    private void editInformation(String goodStatus, String companyName, String stockStatus, Category category, String explanation) {

    }

    public void addComment(String userName, Good good, String commentString, String commentStatus, String title) {
        Comment comment = new Comment(userName, good, commentString, commentStatus, title);
        allComments.add(comment);
    }

    public String getName() {
        return name;
    }

    public String getGoodID() {
        return goodID;
    }

    public String getGoodStatus() {
        return goodStatus.name();
    }

    public String getpointString() {
        return String.valueOf(point);
    }

    public String getCategoryString() {
        return category.getName();
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

    public boolean hasProperty(String property, String value) {
        if (properties.containsKey(property)) {
            if (properties.get(property).equals(value)) {
                return true;
            }
        }

        return false;

    }

    public float getPoint() {
        return point;
    }

    public boolean hasSeller(String seller) {
        if (sellerAndPrices.containsKey(seller)) {
            return true;
        }
        return false;
    }

    public String getProperty(String property) {
        return properties.get(property);
    }

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public String getSummary(Seller seller) {
        return "explanation: " + explanation + "\n" + "Price of this seller: " +
                sellerAndPrices.get(seller.getUserName()) + "\nCategory: " + category + "\nSeller: " + seller +
                "\npoint" + point;
    }

    public static ArrayList<Good> getAllGoods() {
        return allGoods;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public static ArrayList<Good> getConfirmedGoods() {
        return confirmedGoods;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getExplanation() {
        return explanation;
    }

    public int getNumberOfRates() {
        return numberOfRates;
    }
}
