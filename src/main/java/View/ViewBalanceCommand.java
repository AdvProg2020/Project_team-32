package View;

public class ViewBalanceCommand extends Menu{


    public ViewBalanceCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View Balance";
    }

    @java.lang.Override
    public void show() {
        System.out.println(Menu.getUserRecursively(this).getCredit());
    }

    @java.lang.Override
    public void execute() {
        //super.execute();
        parentMenu.show();
        parentMenu.execute();
    }
}
