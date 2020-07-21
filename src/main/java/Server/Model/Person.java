package Server.Model;

import Server.Controller.Exeptions.UserDoesNotExistException;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class Person implements Serializable {


    public static ArrayList<Person> allPersons = new ArrayList<Person>();

    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String phoneID;
    protected String email;
    protected String passWord;
    protected URL imageUrl;
    private HashMap<Discount, Integer> discounts ;
    protected float credit;

    public static boolean hasPersonByUserName(String userName) {
        System.out.println(allPersons);
        for (Person person : allPersons) {
            if (person.userName.equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public void informationEditor(String password,String firstName, String lastName, String phoneId, String email) {
        this.passWord = password;
        this.firstName= firstName;
        this.lastName = lastName;
        this.phoneID = phoneId;
        this.email = email;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneID() {
        return phoneID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneID(String phoneID) {
        this.phoneID = phoneID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person() {

    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public Person(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        allPersons.add(this);
        discounts = new HashMap<Discount, Integer>();
        try {
            imageUrl = new File("src\\main\\resources\\GUIFiles\\icons\\emptyAvatar.png").toURI().toURL();
        } catch (MalformedURLException e) {
            System.err.println("error in loading default avatar");
        }
    }

    public String getPassWord() {
        return passWord;
    }

    public static Person getPersonByUserName(String userName) throws UserDoesNotExistException {
        System.out.println(allPersons);
        for (Person person : allPersons) {
            if (person.userName.equals(userName)) {
                return person;
            }
        }
        throw new UserDoesNotExistException();
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String informationDisplay() {
        return
                "user name=" + userName + '\n' +
                        "first name=" + firstName + '\n' +
                        "last name=" + lastName + '\n' +
                        "phone number=" + phoneID + '\n' +
                        "email=" + email + '\n' +
                        "password=" + passWord + '\n' +
                        "credit=" + credit ;

    }

    public float getCredit() {
        return credit;
    }

    public HashMap<Discount, Integer> getDiscounts() {
        return discounts;
    }

    public void addDiscount(Discount discount, int useNumber) {
        discounts.put(discount,useNumber);
    }
}
