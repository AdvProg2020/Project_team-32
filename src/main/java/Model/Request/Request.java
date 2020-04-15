package Model.Request;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
    private static ArrayList<Request> allRequests;
    public void createARequest(String command){

    }
    private Matcher getMatcher(String command, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        matcher.matches();
        return matcher;
    }

}
