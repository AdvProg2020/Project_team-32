package Controller;

import Model.Off;

public class OffController extends Filterable{

    static {
        selectedGoods.clear();
        for (Off off : Off.getAllOffs()) {
            selectedGoods.addAll(off.getGoodsForOff());
        }
    }

}
