package databaseTest;

import Server.Model.Category;
import Server.Model.Good;
import Server.Model.Seller;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Mockito.mock;

public class StoringGoodsTest {

    @Test
    public void test() {
        Seller seller = mock(Seller.class);
        Category category = mock(Category.class);
        HashMap<String, String> properties = new HashMap<>();
        properties.put("ali", "sharifi");
        new Good("ali", "hello", seller, "apple", category, " ashghal", properties, 10);
    }
}
