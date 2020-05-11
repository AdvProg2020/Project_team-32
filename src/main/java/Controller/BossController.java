package Controller;

import Model.Boss;
import Model.Person;

public class BossController {

    public static void createManager(String[] command) {
        if(Person.getPersonByUserName(command[1])!=null){
            new Boss(command[1], command[2]);
        }
    }

}
