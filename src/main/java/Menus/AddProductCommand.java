package Menus;

import Controller.CategoryController;
import Controller.Exeptions.CategoryNotFindException;
import Controller.Exeptions.DuplicateGoodException;
import Controller.GoodController;
import Model.Category;
import Model.Good;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AddProductCommand extends Menu {
    public AddProductCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("enter product id:");
    }

    @Override
    protected void execute() {
        try {
            String productId = scanner.nextLine();
            if(Good.getGoodFromAllGoods(productId)!=null){
                System.out.println("the id is already exist enter a price to add as its seller or 0 to ignore it:");
                int price = Integer.parseInt(scanner.nextLine());
                if(price > 0){
                    Good.getGoodFromAllGoods(productId).addSellerAndPrice(getUserRecursively(this).getUserName(),price);
                }
            }
            else {
                System.out.println("enter name:");
                String name = scanner.nextLine();
                System.out.println("enter company name:");
                String companyName = scanner.nextLine();
                System.out.println("enter price:");
                int price = Integer.parseInt(scanner.nextLine());
                System.out.println("enter explanation:");
                String explanation = scanner.nextLine();
                System.out.println("enter category");
                Category category = CategoryController.getCategoryByName(scanner.nextLine());
                HashMap<String,String> properties = new HashMap<>();
                for (String specialProperty : category.getSpecialProperties()) {
                    System.out.println("enter " + specialProperty + ":");
                    properties.put(specialProperty,scanner.nextLine());
                }
                GoodController.AddProduct(productId,name,companyName,price,explanation,properties,getUserRecursively(this),category);
            }
        } catch (CategoryNotFindException e) {
            System.out.println("can not find the category.");;
        }

    }
}
