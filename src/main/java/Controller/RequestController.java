package Controller;

import Model.Request;
import Model.RequestPattern;
import Model.Seller;

import java.util.ArrayList;

public class RequestController {

    private static ArrayList<Request> allRequest = new ArrayList<>();

    public static void addOffRequest(String command, Seller seller){
        allRequest.add(new Request(command, RequestPattern.ADD_OFF, seller));
    }

    public static void addEditOffRequest(String command, Seller seller){
        allRequest.add(new Request(command, RequestPattern.EDIT_OFF, seller));
    }







}
