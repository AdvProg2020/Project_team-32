package Controller;

import Controller.Exeptions.WrongPasswordException;
import Controller.Exeptions.DuplicateBossException;
import Controller.Exeptions.DuplicateUsernameException;
import Controller.Exeptions.UserDoesNotExistException;
import Menus.Menu;
import Model.*;

import java.util.ArrayList;

public class AccountController {

    public static boolean isBossCreated = false;

    public static void register(String userName, String accountType, String passWord) throws DuplicateUsernameException, DuplicateBossException {
        if(!Person.hasPersonByUserName(userName)){
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


    public static Person login(String[] command,Guest guest) throws WrongPasswordException, UserDoesNotExistException {
        Person person;
        person = Person.getPersonByUserName(command[1]);
        if(!person.getPassWord().equals(command[2]))
            throw new WrongPasswordException();
        if(person instanceof Customer){
            ((Customer) person).setShoppingBaskets(guest.getShoppingBaskets());
        }
        guest.clearShoppingBaskets();
        return person;
    }

    public static Menu getRelativeMenuForLoginAndSetPerson(Person person) {
        if(person instanceof Boss){
            Menu.bossMenu.setUser((Boss) person);
            return Menu.bossMenu;
        }
        else if (person instanceof Customer){
            Menu.customerMenu.setUser((Customer) person);
            return Menu.customerMenu;
        }
        else if (person instanceof Seller){
            Menu.sellerMenu.setUser((Seller) person);
            return Menu.sellerMenu;
        }
        return null;
    }

    public static void deleteUser(String username) throws UserDoesNotExistException {
        Person person = Person.getPersonByUserName(username);
        Person.allPersons.remove(person);
        if(person instanceof Seller){
            ArrayList<Good> goods= ((Seller) person).getSellingGoods();
            for (Good good : goods) {
                good.removeSeller((Seller) person);
            }
        }
    }

    public static Person getUser(String username) throws UserDoesNotExistException {
        return Person.getPersonByUserName(username);
    }
}
