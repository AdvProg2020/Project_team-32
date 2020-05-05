package Model;

import java.util.ArrayList;


public abstract class Person {
    private static ArrayList<Person> allPersons = new ArrayList<Person>();
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneID;
    private String eMail;
    private String passWord;
    private ArrayList<Discount> discounts;
    private int credit;
    private ArrayList<String> buyLogs;
    private ArrayList<String> sellLogs;

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
