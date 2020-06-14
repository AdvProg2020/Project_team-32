package Controller;

import Controller.Exeptions.WrongPasswordException;
import Controller.Exeptions.DuplicateBossException;
import Controller.Exeptions.DuplicateUsernameException;
import Controller.Exeptions.UserDoesNotExistException;
import View.Menu;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class AccountController  {


    //this field should be a guest in start of program
    public static Person loggedInUser;

    //this filed should be initialize
    public static boolean isBossCreated = false;

    public static void register(String userName, String accountType, String passWord) throws DuplicateUsernameException {
        System.out.println(userName + " " + accountType + " " + passWord);
        if(!Person.hasPersonByUserName(userName)){
            if(accountType.equals("Customer")){
                new Customer(userName, passWord);
            }
            else if(accountType.equals("Seller")){
                new Seller(userName , passWord);
            }
        }
        else {
            throw new DuplicateUsernameException();
        }
    }

    public static Person login(String username,String password) throws WrongPasswordException, UserDoesNotExistException {
        Person person;
        person = Person.getPersonByUserName(username);
        if(!person.getPassWord().equals(password))
            throw new WrongPasswordException();
        if(person instanceof Customer){
            ((Customer) person).setShoppingBaskets(((Guest)loggedInUser).getShoppingBaskets());
        }
        ((Guest)loggedInUser).clearShoppingBaskets();
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
//            launch();
            return Menu.sellerMenu;
        }
        return null;
    }

    public static void deleteUser(Person person) {
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
//    @Override
//    public void start(Stage primaryStage) throws Exception{
////        System.out.println("salam");
//        URL url=  new File("src/main/java/Menus/SellerMenuPack/SellerMenu.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }
}
