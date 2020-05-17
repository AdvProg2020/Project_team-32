package Menus;

import Controller.CategoryController;
import Controller.Exeptions.CategoryNotFindException;
import Controller.Exeptions.InvalidIDException;
import Controller.GoodController;
import Controller.SellerController;
import Model.Category;
import Model.Good;
import Model.Seller;

import java.util.HashMap;

public class EditProductCommand extends Menu {
    public EditProductCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "edit";
    }

    @Override
    protected void show() {
        System.out.println("enter an productId:");
    }

    @Override
    protected void execute() {
        try {
            String productId = scanner.nextLine();
            Good good = SellerController.getGoodFromSellingGood(((Seller)getUserRecursively(this)),productId);
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
            GoodController.editProduct(good,name,companyName,price, (Seller) getUserRecursively(this),explanation,category,properties);
            System.out.println("Request sent successfully.");

        } catch (InvalidIDException e) {
            System.out.println("can not find the product.");
        } catch (CategoryNotFindException e) {
            System.out.println("can not find the category.");;
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
