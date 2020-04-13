package Model;

import Model.Good;
import Model.Person;
import Model.Seller;

import java.util.HashMap;

public class Boss extends Person {

    public Boss(String userName, String firstName, String lastName, String phoneID, String eMail, String passWord, int credit) {
        super(userName, firstName, lastName, phoneID, eMail, passWord, credit);
    }

}