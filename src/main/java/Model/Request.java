package Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
    private static ArrayList<Request> allRequests = new ArrayList<Request>();
    private String request;
    private Pattern pattern;

    public Request(String request) {
        this.request = request;
        allRequests.add(this);
    }

    public static void deleteRequest(Request request) {

    }

    private Matcher getMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        matcher.matches();
        return matcher;
    }

}
