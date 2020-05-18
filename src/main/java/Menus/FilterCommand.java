package Menus;

public abstract class FilterCommand extends Menu{

    public FilterCommand(Menu parentMenu) {
        super(parentMenu);
    }

    @Override
    public void show() {
        System.out.println("Enter an available filter with its value");
    }
}
