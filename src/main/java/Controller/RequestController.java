package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;

public class RequestController {

    private static ArrayList<Request> allRequest = new ArrayList<>();

    public static void addOffRequest(String command, Seller seller){
        allRequest.add(new Request(command, RequestPattern.ADD_OFF, seller));
    }

    public static void addEditOffRequest(String command, Seller seller){
        allRequest.add(new Request(command, RequestPattern.EDIT_OFF, seller));
    }

    public static void addProductRequest(String command, Seller seller){
        allRequest.add(new Request(command, RequestPattern.ADD_PRODUCT, seller));
    }

    public static void addEditProductRequest(String command, Seller seller){
        allRequest.add(new Request(command, RequestPattern.EDIT_PRODUCT, seller));
    }

    public static ArrayList<Request> getAllRequest() {
        return allRequest;
    }

    public static void removeRequest(int index) {
        allRequest.remove(index);
    }

    public static void acceptRequest(Request request) throws Exception {
        if(request.getRequestPattern().equals(RequestPattern.ADD_OFF)){
            createOff(request);
        }
        else if(request.getRequestPattern().equals(RequestPattern.EDIT_OFF)){
            editOff(request);
        }
        else if(request.getRequestPattern().equals(RequestPattern.ADD_PRODUCT)){
            createProduct(request);
        }
        else if(request.getRequestPattern().equals(RequestPattern.EDIT_PRODUCT)){
            editProduct(request);
        }
    }

    private static void editProduct(Request request) {
        //TODO
    }

    private static void createProduct(Request request) {
        //TODO
    }

    private static void editOff(Request request) throws Exception {
        Matcher matcher = request.getMatcher();
        Off off = getOffFromId(matcher.group(1),request);
        String[] goodIds = matcher.group(2).split(",");
        String[] date = matcher.group(3).split(",");
        Date exposeDate = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        ArrayList<Good> goods = new ArrayList<>();
        for (String goodId : goodIds) {
            Good good = Good.getGoodById(goodId);
            if(good == null)
                throw new Exception();
            goods.add(good);
        }
        int offPercent = Integer.parseInt(matcher.group(4));
        off.editInformation(goods,exposeDate,offPercent);
    }

    private static Off getOffFromId(String offId,Request request) throws Exception {
        ArrayList<Off> offs = request.getSeller().getOffs();
        for (Off off : offs) {
            off.getOffID().equals(offId);
            return off;
        }
        throw new Exception();
    }

    private static void createOff(Request request) throws Exception{
        Matcher matcher = request.getMatcher();
        String offId = matcher.group(1);
        String[] goodIds = matcher.group(2).split(",");
        String[] date = matcher.group(3).split(",");
        Date exposeDate = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        ArrayList<Good> goods = new ArrayList<>();
        for (String goodId : goodIds) {
            Good good = Good.getGoodById(goodId);
            if(good == null)
                throw new Exception();
            goods.add(good);
        }
        int offPercent = Integer.parseInt(matcher.group(4));
        request.getSeller().makeOff(new Off(offId,goods,exposeDate,offPercent));
    }
}
