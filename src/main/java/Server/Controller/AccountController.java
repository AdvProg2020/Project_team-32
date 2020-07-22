package Server.Controller;

import Server.Controller.Exeptions.WrongPasswordException;
import Server.Controller.Exeptions.DuplicateUsernameException;
import Server.Controller.Exeptions.UserDoesNotExistException;
import Server.Model.*;

import java.util.ArrayList;

public class AccountController {


    //this field should be a guest in start of program
    public static Person loggedInUser;

    public static void register(String userName, String accountType, String passWord) throws DuplicateUsernameException {
        if (!Person.hasPersonByUserName(userName)) {
            if (accountType.equals("Customer")) {
                new Customer(userName, passWord);
            } else if (accountType.equals("Seller")) {
                new Seller(userName, passWord);
            }
        } else {
            throw new DuplicateUsernameException();
        }
    }

    public static Person login(String username, String password) throws WrongPasswordException, UserDoesNotExistException {
        Person person;
        person = Person.getPersonByUserName(username);
        if (!person.getPassWord().equals(password))
            throw new WrongPasswordException();
        /*if (person instanceof Customer) {
            ((Customer) person).setShoppingBaskets(((Guest) loggedInUser).getShoppingBaskets());
        }*/
        //TODO out of comment
        //((Guest) loggedInUser).clearShoppingBaskets();
        return person;
    }

    public static void deleteUser(String username) throws UserDoesNotExistException {
        Person person = Person.getPersonByUserName(username);
        Person.allPersons.remove(person);
        if (person instanceof Seller) {
            ArrayList<Good> goods = ((Seller) person).getSellingGoods();
            for (Good good : goods) {
                good.removeSeller((Seller) person);
            }
        }
    }

    public static ArrayList<Customer> getAllCustomer() {

        ArrayList<Person> list = Person.allPersons;
        ArrayList<Customer> customers = new ArrayList<>();

        for (Person person : list) {
            if (person instanceof Customer) {
                customers.add((Customer) person);
            }
        }

        return customers;

    }

    public static void logout() {
        loggedInUser = new Guest();
    }
}
