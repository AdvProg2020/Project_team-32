package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends  Person{
    private ArrayList<Logs> sellingGoods;
    private String factoryName;
    public Seller(String userName, String password) {
        super(userName, password);
    }
}