package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RequestPattern {

    ADD_PRODUCT("^(\\S+) (\\S+) (\\d+) (\\S+) (\\S+) (\\S+:\\S+,)+ (.+)$","add product request"), //goodId name price companyName status properties explanation
    EDIT_PRODUCT( "^(\\S+) (\\d+) (\\S+) (\\S+) (\\S+:\\S+,)+ (.+)$", "edit product request"),//name price companyName status properties explanation
    ADD_OFF("^(\\S+) (\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+)$","add off request"),//[offID] [GoodId1,Goodid2,..] [year,month,day] [meghdarharaj]
    EDIT_OFF("^(\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+)$", "edit off request")////[GoodId1,goodid2,..] [year,month,day] [meghdarharaj]
    ;

    Pattern pattern;
    String type;

    RequestPattern(String pattern, String type) {
        this.pattern = Pattern.compile(pattern);
        this.type = type;
    }

}
