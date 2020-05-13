package Menus;

import Controller.Exeptions.InvalidCommandException;
import Model.Good;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Date;

public class EditOffCommand extends Menu {

    public EditOffCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("please enter number you chose");
    }

    @Override
    protected void execute() {
        int n = Integer.parseInt(scanner.nextLine());
        try{

        }
        catch ()





        System.out.println("please enter new expose date in this format year/month/day");
        String exposeDate = scanner.nextLine();
        try{

        }catch (){

        }

        System.out.println("please enter new discount percentage ");
        try{

        }catch (){

        }

        while (true) {
            System.out.println("add or delete or exit ");
            String command = scanner.nextLine();
            if(command.trim().matches("add")){
                System.out.println("please enter GoodID");
                String ID =scanner.nextLine();

            }else if(command.trim().matches("delete")){

            }else if(command.trim().matches("exit")){
                break;
            }
            else System.out.println("invalid command");
        }
//        private String offID;
//        private ArrayList<Good> goodsForOff;
//        private String offStatus;
//        private Date initialDate;
//        private Date exposeDate;
//        private int discountPercent;

    }
}
