package View;

import Controller.OffController;

public class OffMenuFilterCommand extends FilterCommand {

    public OffMenuFilterCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "off menu filter";
    }

    @Override
    public void execute() {
        String filter = scanner.nextLine();
        String value = scanner.nextLine();
        try {
            OffController.filter(filter, value);
        } catch (Exception e) {
            System.out.println("CannotFilter");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
