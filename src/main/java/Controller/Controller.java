package Controller;

import View.*;
import View.BossPage.BossMenu;
import Model.Category;
import Model.Good;
import Model.Person;

import java.io.*;
import java.util.ArrayList;

public class Controller {

    public static void main(String[] args) {
        Menu.bossMenu = new BossMenu(null);
        Menu.guestMenu = new GuestMenu(null);
        Menu.customerMenu = new CustomerMenu( null);
        Menu.sellerMenu = new SellerMenu(null);
        Menu.individualGoodMenu = new IndividualGoodMenu(null);
        importData();
        Menu.guestMenu.show();
        Menu.guestMenu.execute();
        exportData();
    }

    private static void importData(){
        importDataEach(Person.allPersons,"src/main/resources/database/accounts.txt");
        importDataEach(BossController.getAllDiscount(),"src/main/resources/database/discounts.txt");
        importDataEach(RequestController.getAllRequest(), "src/main/resources/database/requests.txt");
        importDataEach(Category.getAllCategories(), "src/main/resources/database/categories.txt");
        importDataEach(Good.confirmedGoods, "src/main/resources/database/confirmedGoods.txt");
        importDataEach(Good.getAllGoods(), "src/main/resources/database/allGoods.txt");    }

    private static void exportData(){
        for (Person person : Person.allPersons) {
            System.out.println(person);
        }
        exportDataEach(Person.allPersons,"src/main/resources/database/accounts.txt");
        exportDataEach(BossController.getAllDiscount(),"src/main/resources/database/discounts.txt");
        exportDataEach(RequestController.getAllRequest(), "src/main/resources/database/requests.txt");
        exportDataEach(Category.getAllCategories(), "src/main/resources/database/categories.txt");
        exportDataEach(Good.confirmedGoods, "src/main/resources/database/confirmedGoods.txt");
        exportDataEach(Good.getAllGoods(), "src/main/resources/database/allGoods.txt");
        //TODO other list
    }

    private static void exportDataEach(ArrayList list, String outputAddress){
        try {
            OutputStream outputFile = new FileOutputStream(outputAddress);
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outputFile);
            for (Object object : list) {
                objectOutputFile.writeObject(object);
            }
            objectOutputFile.flush();
            objectOutputFile.close();
            outputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void importDataEach(ArrayList list, String inputAddress){
        try {
            FileInputStream inputFile = new FileInputStream(inputAddress);
            ObjectInputStream input = new ObjectInputStream(inputFile);
            while (true){
                Object object = input.readObject();
                if(object == null){
                    break;
                }
                list.add(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
