package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RequestPattern {

    ADD_PRODUCT("^(\\S+) (\\S+) (\\d+) (\\S+) (\\S+) (\\S+) (\\S+:\\S+,)+ (.+)$","add product request"),
    EDIT_PRODUCT( "^(\\S+) (\\d+) (\\S+) (\\S+) (\\S+) (\\S+:\\S+,)+ (.+)$", "edit product request"),
    ADD_OFF("^(\\S+) (\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+)$","add off request"),
    EDIT_OFF("^(\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+)$", "edit off request")
    ;

    Pattern pattern;
    String type;

    RequestPattern(String pattern, String type) {
        this.pattern = Pattern.compile(pattern);
        this.type = type;
    }

}
