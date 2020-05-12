package Controller;

import Controller.Exeptions.DuplicateBossException;
import Controller.Exeptions.DuplicateUsernameException;
import Model.Boss;
import Model.Person;

public class BossController {

    public static void createManager(String[] command) throws DuplicateUsernameException {
        if(!Person.hasPersonByUserName(command[1])){
            new Boss(command[1], command[2]);
        }
        else{
            throw new DuplicateUsernameException();
        }
    }

}
