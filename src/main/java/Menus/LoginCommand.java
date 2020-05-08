package Menus;

import Model.Boss;
import Model.Customer;
import Model.Person;
import Model.Seller;

import java.util.ArrayList;

public class LoginCommand extends Menu{
    public LoginCommand(String name) {
        super(name, null);
    }


    @Override
    protected void show() {
        System.out.println("Please use this format to register:\n login [username] [password]");
    }

    @Override
    protected void execute() {
        String command = scanner.nextLine();
        Person person;
        if(commandValidation(command)){
            if((person = getPerson(command.split(" ")))!=null){
                System.out.println("Login successfully done");
                callRelativeMenu(person);
            }
            else {
                System.out.println("username or password is not correct");
            }
        }
        else {
            this.show();
            this.execute();
        }
    }

    private void callRelativeMenu(Person person) {
        if(person instanceof Boss){
            bossMenu.setUser((Boss) person);
            bossMenu.show();
            bossMenu.execute();
        }
        else if (person instanceof Customer){
            customerMenu.setUser((Customer) person);
            customerMenu.show();
            customerMenu.execute();
        }
        else if (person instanceof Seller){
            sellerMenu.setUser((Seller) person);
            sellerMenu.show();
            sellerMenu.execute();
        }

    }

    private Person getPerson(String[] command) {
        return Person.login(command[1], command[2]);
    }

    private boolean commandValidation(String command) {
        return command.matches("login \\S+ \\S+");
    }
  
    public void login(){
        String username = scanner.nextLine();
        String password = scanner.nextLine();
    }

}
