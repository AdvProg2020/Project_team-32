package Server.Controller;

import Server.Model.*;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private static Date date = null;

    private static boolean isBossCreated;

    public static void initialize(){
        (new Thread() {
            public void run() {

                Media sound =null;
                MediaPlayer mediaPlayer =null;
                while (true){
                    sound = new Media(new File("src\\main\\resources\\GUIFiles\\SoundEffects\\back_music1.mp3").toURI().toString());
                    mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.setVolume(0.03);
                    mediaPlayer.play();
                    try {
                        Thread.sleep(120000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sound = new Media(new File("src\\main\\resources\\GUIFiles\\SoundEffects\\back_music2.mp3").toURI().toString());
                    mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.setVolume(0.03);
                    mediaPlayer.play();
                    try {
                        Thread.sleep(120000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        //set isBossCreated
        isBossCreated = isBossCreated();

        AccountController.loggedInUser = new Guest();

        //set mainCategory
        Category.rootCategory = Category.getCategoryByName("mainCategory");
        if(Category.rootCategory == null){
           Category.rootCategory = new Category("mainCategory", null , null);
        }

    }

    private static boolean isBossCreated() {
        for (Person person : Person.allPersons) {
            if(person instanceof Boss)
                return true;
        }
        return false;
    }

    public static void importData(){
        importDataEach(Person.allPersons,"src/main/resources/database/accounts.txt");
        importDataEach(BossController.getAllDiscount(),"src/main/resources/database/discounts.txt");
        importDataEach(RequestController.getAllRequest(), "src/main/resources/database/requests.txt");
        importDataEach(Category.getAllCategories(), "src/main/resources/database/categories.txt");
        importDataEach(Good.confirmedGoods, "src/main/resources/database/confirmedGoods.txt");
        importDataEach(Good.getAllGoods(), "src/main/resources/database/allGoods.txt");
        importDataEach(Off.getAllOffs(),"src/main/resources/database/allOffs.txt");
    }

    public static void exportData(){
        exportDataEach(Person.allPersons,"src/main/resources/database/accounts.txt");
        exportDataEach(BossController.getAllDiscount(),"src/main/resources/database/discounts.txt");
        exportDataEach(RequestController.getAllRequest(), "src/main/resources/database/requests.txt");
        exportDataEach(Category.getAllCategories(), "src/main/resources/database/categories.txt");
        exportDataEach(Good.confirmedGoods, "src/main/resources/database/confirmedGoods.txt");
        exportDataEach(Good.getAllGoods(), "src/main/resources/database/allGoods.txt");
        exportDataEach(Off.getAllOffs(),"src/main/resources/database/allOffs.txt");
        //TODO other list
    }

    private static void exportDataEach(ArrayList list, String outputAddress){
        try {
            OutputStream outputFile = new FileOutputStream(outputAddress);
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outputFile);
            for (Object object : list) {
                objectOutputFile.writeObject(object);
            }
            objectOutputFile.flush();
            objectOutputFile.close();
            outputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void importDataEach(ArrayList list, String inputAddress){
        try {
            FileInputStream inputFile = new FileInputStream(inputAddress);
            ObjectInputStream input = new ObjectInputStream(inputFile);
            while (true){
                Object object = input.readObject();
                if(object == null){
                    break;
                }
                list.add(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void sound(int voice){
        Media sound =null;
        switch (voice){
            case 1:
                sound = new Media(new File("src\\main\\resources\\GUIFiles\\SoundEffects\\sound_ex_machina_Button_Click.mp3").toURI().toString());
                break;
            case 2:
                sound= new Media(new File("src\\main\\resources\\GUIFiles\\SoundEffects\\transportation_car_seat_belt_click_lock_002.mp3").toURI().toString());
                break;
            case 3:
                sound= new Media(new File("src\\main\\resources\\GUIFiles\\SoundEffects\\click.mp3").toURI().toString());
                break;
            default:
                sound = new Media(new File("src\\main\\resources\\GUIFiles\\SoundEffects\\zapsplat_impact_rock_small_hit_solid_ground_004_11181.mp3").toURI().toString());
        }
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }

    public static void setIsBossCreated(boolean isBossCreated) {
        Controller.isBossCreated = isBossCreated;
    }

    public static boolean hasBossCreated() {
        return isBossCreated;
    }
}
