package Model;

import java.util.ArrayList;


public abstract class Person {

  
    protected static ArrayList<Person> allPersons = new ArrayList<Person>();
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


    public void informationEditor() {

    }

    public Person() {

    }

    public Person(String userName, String passWord) {
        //super(shoppingBasket);
        this.userName = userName;
        this.passWord = passWord;
    }

    public ArrayList<BuyLog> getBuyLogs() {
        return buyLogs;
    }

    public ArrayList<SellLog> getSellLogs() {
        return sellLogs;
    }
    public static boolean register(String userName, String accountType, String passWord){
        if(getPersonByUserName(userName) != null){
            if(accountType.equals("boss")){
                new Boss(userName, passWord);
            }
            else if(accountType.equals("customer")){
                new Customer(userName, passWord);
            }
            else if(accountType.equals("seller")){
                new Seller(userName , passWord);
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
        // a method should call for changing file
        return true;
    }

    private static Person getPersonByUserName(String userName) {
        for (Person person : allPersons) {
            if(person.userName.equals(userName)) {
                return person;
            }
        }
        return null;

}
