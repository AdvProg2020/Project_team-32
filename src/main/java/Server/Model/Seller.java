package Server.Model;

import Server.Controller.Exeptions.MultipleAuctionException;

import java.util.ArrayList;
import java.util.Date;

public class Seller extends Person {


    private ArrayList<Good> sellingGoods;
    private String factoryName;
    private ArrayList<Off> offs;
    private ArrayList<SellLog> allSellingLogs;
    private static ArrayList<Seller> allSellers = new ArrayList<>();
    private Auction auction;

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) throws MultipleAuctionException {
        if (auction!=null) this.auction = auction;
        else throw new MultipleAuctionException();
    }

    public static void seller(Seller seller){
        seller.factoryName = "apple";
        Good good = new Good("khiyar","123",new Seller("ali","123"),"salma",new Category("hey",null,null),null,null,0);
        seller.sellingGoods.add(good);
        SellLog sellLog = new SellLog("1234",new Date(),123,324,good,"oskol","pashm");
        seller.allSellingLogs.add(sellLog);

    }


    public ArrayList<SellLog> getAllSellingLogs() {
        return allSellingLogs;
    }

    public Seller(String userName, String password) {
        super(userName, password);
        this.sellingGoods = new ArrayList<Good>();
        this.offs= new ArrayList<>();
        this.allSellingLogs = new ArrayList<SellLog>();
        allSellers.add(this);
    }

    public ArrayList<Off> getOffs() {
        return offs;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public ArrayList<Good> getSellingGoods() {
        return sellingGoods;
    }

    public Good getGoodByID(String goodID) {
        for (Good sellingGood : sellingGoods) {
            if (sellingGood.getGoodID().equals(goodID)) return sellingGood;
        }
        return null;
    }

    public String viewIndividualProduct(Good good) {
        return good.toString();
    }

    public void viewIndividualProductsBuyers(Good good) {

    }

    public void addProduct(Good good){
        sellingGoods.add(good);
    }

    public void makeOff(Off off) {
        offs.add(off);
    }

    public void editOff(Off off) {

    }

    public void selling(Good good) {

    }

    public void makeGoodEditRequest(Good good) {

    }

    public void deleteGood(Good good) {
        sellingGoods.remove(good);
    }

    public static ArrayList<Seller> getAllSellers() {
        return allSellers;
    }
}