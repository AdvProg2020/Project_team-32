package View;

import Controller.CategoryController;
import Controller.Exeptions.CategoryNotFindException;
import Controller.GoodController;
import Model.Category;
import Model.Good;

import java.util.HashMap;

public class AddProductCommand extends Menu {
    public AddProductCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Add product";
    }

    @Override
    public void show() {
        System.out.println("enter product id:");
    }

    @Override
    public void execute() {
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
                GoodController.getGoodController().AddProduct(productId,name,companyName,price,explanation,properties,getUserRecursively(this),category);
            }
        } catch (CategoryNotFindException e) {
            System.out.println("can not find the category.");;
        }

    }
}
