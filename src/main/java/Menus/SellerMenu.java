package Menus;

import Model.Seller;

public class SellerMenu extends Menu{

    private Seller user;

    public SellerMenu(Menu parentMenu) {
        super(parentMenu);
        this.name ="Seller Menu";
        subMenu.add(new InformationMenu( this));
        subMenu.add(new ViewCompanyInformaionCommand( this));
        subMenu.add(new ViewSalesHistoryCommand( this));
        subMenu.add(new ManageProductsMenu( this));
        subMenu.add(new RemoveProductBySellerCommand( this));
        subMenu.add(new AddProductCommand( this));
        subMenu.add(new ShowCategoryCommandBySeller( this));
        subMenu.add(new ViewOffCommand( this));
        subMenu.add(new ViewBalanceCommand( this));
    }

    @Override
    protected void show() {

        for(int i=0 ; i < subMenu.size() ; i++) {
            System.out.println("" + i + "." + subMenu.get(i));
        }
        System.out.println(""+ subMenu.size() + ".back");
        System.out.println("Please select a number:");
    }

    @Override
    protected void execute() {
        super.execute();
    }

    public void setUser(Seller user) {
        this.user = user;
    }

    public Seller getUser() {
        return user;
    }
}
