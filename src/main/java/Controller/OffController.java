package Controller;

import Model.Off;

public class OffController extends Filterable{

    private static OffController offController;

    public static OffController getOffController() {
        if(offController == null) {
            offController = new OffController();
            offController.reset();
        }
        return offController;
    }

    @Override
    public void reset() {
        selectedGoods.clear();
        for (Off off : Off.getAllOffs()) {
            selectedGoods.addAll(off.getGoodsForOff());
        }
    }
}
