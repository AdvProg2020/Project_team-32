package Controller;

import Model.Request;
import Model.RequestPattern;

import java.util.ArrayList;

public class RequestController {

    private static ArrayList<Request> allRequest = new ArrayList<>();

    public static void addOffRequest(String command){
        allRequest.add(new Request(command, RequestPattern.ADD_OFF));
    }

    public static void addEditOffRequest(String command){
        allRequest.add(new Request(command, RequestPattern.EDIT_OFF));
    }







}
