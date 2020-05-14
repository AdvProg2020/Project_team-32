package Controller.Sort;

import Model.Good;

import java.util.Comparator;

public class SortByPoint implements Comparator<Good> {

    @Override
    public int compare(Good good, Good t1) {
        return good.getPoint() - t1.getPoint();
    }
    
}
