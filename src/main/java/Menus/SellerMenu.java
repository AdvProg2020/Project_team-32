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
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    public void setUser(Seller user) {
        this.user = user;
    }

    public Seller getUser() {
        return user;
    }
}
