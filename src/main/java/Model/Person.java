package Model;

import java.util.ArrayList;


public abstract class Person {
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String phoneID;

    protected String eMail;
    protected String passWord;
    private ArrayList<Discount> discounts;
    protected int credit;
    protected ArrayList<BuyLog> buyLogs;
    protected ArrayList<SellLog> sellLogs;


    public void informationEditor() {

    }

    public Person() {

    }

    public Person(String userName, String firstName, String lastName, String phoneID, String eMail, String passWord, int credit) {
        //super(shoppingBasket);
        this.credit = credit;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneID = phoneID;
        this.eMail = eMail;
        this.passWord = passWord;
    }

    public ArrayList<BuyLog> getBuyLogs() {
        return buyLogs;
    }

    public ArrayList<SellLog> getSellLogs() {
        return sellLogs;
    }

    //yasin
    private void register(String userName, String passWord) {

    }

}
