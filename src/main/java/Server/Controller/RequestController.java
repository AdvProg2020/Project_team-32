package Server.Controller;

import Server.Model.*;

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

    public static void removeRequest(Request request) {
        allRequest.remove(request);
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
        removeRequest(request);
    }

    private static void editProduct(Request request) {
        Good editGood = Good.getGoodFromAllGoods(request.getMatcher().group(1)+"edited");
        Good good = Good.getGoodFromAllGoods(request.getMatcher().group(1));
        System.out.println(good);
        System.out.println(editGood);
        System.out.println(request.getMatcher().group(1));
        good.editInfo(editGood,request.getSeller());
        GoodController.getGoodController().deleteGood(editGood);
        good.confirmStatus();
    }

    private static void createProduct(Request request) {
       Good good = Good.getGoodFromAllGoods(request.getMatcher().group(1));
       good.confirmStatus();
       good.getCategory().addProduct(good);
       request.getSeller().addProduct(good);
    }

    private static void editOff(Request request) throws Exception {
        Matcher matcher = request.getMatcher();
        Off off = getOffFromId(matcher.group(1),request);
        String[] goodIds = matcher.group(2).split(",");
        String[] date = new String[3];
        date[0] = matcher.group(3);
        date[1] = matcher.group(4);
        date[2] = matcher.group(5);
        Date exposeDate = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        date[0] = matcher.group(6);
        date[1] = matcher.group(7);
        date[2] = matcher.group(8);
        Date initialDate = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        ArrayList<Good> goods = new ArrayList<>();
        for (String goodId : goodIds) {
            Good good = Good.getGoodById(goodId);
            System.out.println(goodId);
            if(good == null)
                throw new Exception();
            goods.add(good);
        }
        int offPercent = Integer.parseInt(matcher.group(9));
        off.editInformation(goods,exposeDate,initialDate,offPercent);
        System.out.println("edit successful");
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
        System.out.println(request.getRequest());


        String offId = matcher.group(1);
        String[] goodIds = matcher.group(2).split(",");
        String[] date = new String[3];
        date[0] = matcher.group(3);
        date[1] = matcher.group(4);
        date[2] = matcher.group(5);
        Date exposeDate = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        date[0] = matcher.group(6);
        date[1] = matcher.group(7);
        date[2] = matcher.group(8);
        Date initialDate = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        ArrayList<Good> goods = new ArrayList<>();
        for (String goodId : goodIds) {
            Good good = Good.getGoodById(goodId);
            System.out.println(goodId);
            if(good == null)
                throw new Exception();
            goods.add(good);
        }
        int offPercent = Integer.parseInt(matcher.group(9));
        Off off = new Off(offId,goods,exposeDate,initialDate,offPercent);
        Off.addOff(off);
        OffController.getOffController().addToSelectedGoods(off.getGoodsForOff());
        request.getSeller().makeOff(off);
        System.out.println("add soff succesful");
    }

    public static Request getRequestByIndex(int requestIndex) {
        return allRequest.get(requestIndex);
    }
}
