package Controller;

import Model.Category;
import Model.Good;
import Model.Off;

import java.util.ArrayList;
import java.util.HashMap;

public class OffController extends Filterable{

    private static OffController offController;

    public static OffController getOffController() {
        if(offController == null) {
            offController = new OffController();
        }
        return offController;
    }

    @Override
    public void reset() {
        for (Off off : Off.getAllOffs()) {
            selectedGoods.addAll(off.getGoodsForOff());
        }
    }

    public void addToSelectedGoods(ArrayList<Good> good) {
        selectedGoods.addAll(good);
    }

    public OffController() {
        selectedGoods = new ArrayList<>();
        currentCategory = Category.rootCategory;
        currentFilters = new HashMap<>();
        reset();
    }
}
