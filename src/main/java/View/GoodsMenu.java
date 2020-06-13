package View;

public class GoodsMenu extends Menu {


    public GoodsMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "Goods Menu";
        this.subMenu.add(new ViewCategoriesCommand(this));
        this.subMenu.add(new FilteringMenu(this));
        this.subMenu.add(new SortingMenu(this));
        this.subMenu.add(new ShowProductsCommand(this));
        this.subMenu.add(new ShowProductCommand(this));
    }
    

}
