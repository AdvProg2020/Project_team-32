package Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {

    private String request;
    private RequestPattern requestPattern;
    private Matcher matcher;

    public Request(String request, RequestPattern requestPattern) {
        this.request = request;
        this.requestPattern = requestPattern;
        matcher =  requestPattern.pattern.matcher(request);
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public String getRequestType() {
        return requestPattern.type;
    }

    public String getRequest() {
        return request;
    }
}
