package Server.Model;

import java.io.Serializable;
import java.util.regex.Matcher;

public class Request implements Serializable {

    private String request;
    private RequestPattern requestPattern;
    private Seller seller;

    public Request(String request, RequestPattern requestPattern, Seller seller) {
        this.seller = seller;
        this.request = request;
        this.requestPattern = requestPattern;
    }

    public Matcher getMatcher() {
        Matcher matcher = requestPattern.pattern.matcher(request);
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
