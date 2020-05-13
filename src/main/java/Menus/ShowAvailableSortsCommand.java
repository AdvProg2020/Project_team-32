package Menus;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.ArrayList;

public class ShowAvailableSortsCommand extends Menu {

    public ShowAvailableSortsCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("Time");
        System.out.println("Point");
        System.out.println("Number of view");
    }

    @Override
    protected void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}

//subMenu of sorting