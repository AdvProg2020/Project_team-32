package Menus.InformationMenus;

import Menus.Menu;

public class InformationEditorCommand extends Menu {

    public InformationEditorCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "edit information";
    }

    @Override
    public void show() {
        System.out.println("Please edit your information:");
    }

    @Override
    public void execute() {
        String password;
        String firstName;
        String lastName;
        String phoneId;
        String email;
        System.out.println("password:");
        password = scanner.nextLine();
        System.out.println("first name:");
        firstName = scanner.nextLine();
        System.out.println("last name:");
        lastName = scanner.nextLine();
        System.out.println("phone number:");
        phoneId = scanner.nextLine();
        System.out.println("email:");
        email = scanner.nextLine();
        getUserRecursively(this).informationEditor(password, firstName, lastName, phoneId, email);
        parentMenu.show();
        parentMenu.execute();
    }
}
