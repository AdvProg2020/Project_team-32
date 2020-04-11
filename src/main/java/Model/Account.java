package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Account extends Person {
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneID;
    
    private String eMail;
    private String passWord;
    private ArrayList<Discount> discounts ;
    private int credit;
    private ArrayList<String> buyLogs;
    private ArrayList<String> sellLogs;

    public Account(HashMap<Good, Salesman> shoppingBasket, String userName, String firstName, String lastName, String phoneID, String eMail, String passWord) {
        super(shoppingBasket);
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneID = phoneID;
        this.eMail = eMail;
        this.passWord = passWord;
    }
}
