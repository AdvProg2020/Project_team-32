package View;

import Controller.GoodController;
import Controller.Sort.SortType;

public class SortCommand extends Menu {

    public SortCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Sort command";
    }

    @Override
    public void show() {
        System.out.println("1. sort by time \n2. sort by point \n3.sort by number of views");
    }

    @Override
    public void execute() {
        try {
            GoodController.getGoodController().sort(getTypeOfSortWithInt(Integer.parseInt(scanner.nextLine())));
        } catch (Exception e) {
            System.out.println("invalidSortMethod");
        }
        parentMenu.show();
        parentMenu.execute();
    }



    public static SortType getTypeOfSortWithInt(int i){
        if( i == 1 ){
            return SortType.sortByTime;
        } else if (i == 2){
            return SortType.sortByPoint;
        } else {
            return SortType.sortByNumberOfView;
        }
    }
}
//subMenu of SortingMenu
