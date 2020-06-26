package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RequestPattern {

    ADD_PRODUCT("(.+)","add product request"), //goodId
    EDIT_PRODUCT( "(.+)", "edit product request"),//goodId
    ADD_OFF("(\\S+) (\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) (\\d+)","add off request"),//[offID] [GoodId1,Goodid2,..] [year,month,day] [year,month,day] [meghdarharaj]
    EDIT_OFF("(\\S+) (\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) (\\d+)", "edit off request")////[offID] [GoodId1,goodid2,..] [year,month,day] [year,month,day] [meghdarharaj]
    ;
    Pattern pattern;
    String type;

    RequestPattern(String pattern, String type) {
        this.pattern = Pattern.compile(pattern);
        this.type = type;
    }

}
