package Server.Model;

import java.io.File;
import java.util.HashMap;

public class FileProduct extends Good {

    private File file;

    public FileProduct(String name, String goodID, Seller seller, String companyName, Category category, String explanation, HashMap<String, String> properties, int price,File file) {
        super(name, goodID, seller, companyName, category, explanation, properties, price);
        this.file = file;
    }
}
