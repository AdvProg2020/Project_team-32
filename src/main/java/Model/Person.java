package Model;

import java.util.ArrayList;


public abstract class Person {

  
    public static ArrayList<Person> allPersons = new ArrayList<Person>();
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

    public static Person login(String userName, String passWord) {
        Person person;
        person = getPersonByUserName(userName);
        if(person == null)
            return null;
        if(!person.passWord.equals(passWord))
            return null;
        return person;
    }


    public void informationEditor(String password,String firstName, String lastName, String phoneId, String email) {
        this.passWord = password;
        this.firstName= firstName;
        this.lastName = lastName;
        this.phoneID = phoneId;
        this.eMail = email;
    }

    public Person() {

    }

    public Person(String userName, String passWord) {
        //super(shoppingBasket);
        this.userName = userName;
        this.passWord = passWord;
        allPersons.add(this);
        discounts = new ArrayList<Discount>();
        buyLogs = new ArrayList<BuyLog>();
        sellLogs = new ArrayList<SellLog>();
    }

    public ArrayList<BuyLog> getBuyLogs() {
        return buyLogs;
    }

    public ArrayList<SellLog> getSellLogs() {
        return sellLogs;
    }

    public String getPassWord() {
        return passWord;
    }

    public static Person getPersonByUserName(String userName) {
        for (Person person : allPersons) {
            if (person.userName.equals(userName)) {
                return person;
            }
        }
        return null;
    }


    public String informationDisplay() {
        return
                "user name=" + userName + '\n' +
                        "first name=" + firstName + '\n' +
                        "last name=" + lastName + '\n' +
                        "phone number=" + phoneID + '\n' +
                        "email=" + eMail + '\n' +
                        "password=" + passWord + '\n' +
                        "credit=" + credit ;

    }

}
