package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request implements Serializable {

    private String request;
    private RequestPattern requestPattern;
    private Matcher matcher;
    private Seller seller;

    public Request(String request, RequestPattern requestPattern, Seller seller) {
        this.seller = seller;
        this.request = request;
        this.requestPattern = requestPattern;
        matcher = requestPattern.pattern.matcher(request);
    }

    public Matcher getMatcher() {
        matcher.matches();
        return matcher;
    }

    public RequestPattern getRequestPattern() {
        return requestPattern;
    }

    public Seller getSeller() {
        return seller;
    }

    public String getRequestType() {
        return requestPattern.type;
    }

    public String getRequest() {
        return request;
    }

    public String getSellerName(){
        return seller.getUserName();
    }

}
