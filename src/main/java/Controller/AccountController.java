package Controller;

import Controller.Exeptions.DuplicateBossException;
import Controller.Exeptions.DuplicateUsernameException;
import Menus.Menu;
import Model.Boss;
import Model.Customer;
import Model.Person;
import Model.Seller;
import com.sun.jdi.InvalidTypeException;

public class AccountController {

    public static boolean isBossCreated = false;

    public static void register(String userName, String accountType, String passWord) throws DuplicateUsernameException, DuplicateBossException {
        if(Person.getPersonByUserName(userName) != null){
            if(accountType.equals("boss") && !isBossCreated){
                new Boss(userName, passWord);
                isBossCreated = true;
            }
            else if(accountType.equals("customer")){
                new Customer(userName, passWord);
            }
            else if(accountType.equals("seller")){
                new Seller(userName , passWord);
            }
            else if(isBossCreated){
                throw new DuplicateBossException();
            }
        }
        else {
            throw new DuplicateUsernameException();
        }
        // a method should call for changing file
    }


    public static Person login(String[] command) {
        Person person;
        person = Person.getPersonByUserName(command[1]);
        if(person == null)
            return null;
        if(!person.getPassWord().equals(command[2]))
            return null;
        return person;
    }

    public static Menu getRelativeMenuForLogin(Person person) {
        if(person instanceof Boss){
            return Menu.bossMenu;
        }
        else if (person instanceof Customer){
            return Menu.customerMenu;
        }
        else if (person instanceof Seller){
            return Menu.sellerMenu;
        }
        return null;
    }

    public static void deleteUser(String username) {
        Person.allPersons.remove(Person.getPersonByUserName(username));
    }

    public static Person getUser(String username) {
        return Person.getPersonByUserName(username);
    }
}
