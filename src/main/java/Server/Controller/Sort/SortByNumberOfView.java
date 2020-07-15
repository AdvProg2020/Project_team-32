package Server.Controller.Sort;

import Server.Model.Good;

import java.util.Comparator;

public class SortByNumberOfView implements Comparator<Good> {
    @Override
    public int compare(Good good, Good t1) {
        return good.getNumberOfViews() - t1.getNumberOfViews();
    }
}
