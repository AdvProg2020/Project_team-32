package View;

import Model.Seller;

public class ViewCompanyInformaionCommand extends Menu{
    public ViewCompanyInformaionCommand(Menu parentMenu) {
        super(parentMenu);
        this.name ="View Company Information";
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() {
        System.out.println(( (Seller)getUserRecursively(this)).getFactoryName());
        parentMenu.show();
        parentMenu.execute();
    }

}
