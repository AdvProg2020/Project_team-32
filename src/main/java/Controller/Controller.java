package Controller;

import Model.Guest;
import View.*;
import View.BossPage.BossMenu;
import Model.Category;
import Model.Good;
import Model.Person;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private static Date date = null;

    //
    public boolean isBossCreated;

    public static void main(String[] args) {
        Menu.bossMenu = new BossMenu(null);
        Menu.guestMenu = new GuestMenu(null);
        Menu.customerMenu = new CustomerMenu( null);
        Menu.sellerMenu = new SellerMenu(null);
        Menu.individualGoodMenu = new IndividualGoodMenu(null);
        importData();
        Menu.guestMenu.show();
        Menu.guestMenu.execute();
        exportData();
    }

    public static void initialize(){
        (new Thread() {
            public void run() {

                Media sound =null;
                MediaPlayer mediaPlayer =null;
                while (true){
                    sound = new Media(new File("src\\main\\resources\\GUIFiles\\SoundEffects\\back_music1.mp3").toURI().toString());
                    mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.play();
                    try {
                        Thread.sleep(120000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sound = new Media(new File("src\\main\\resources\\GUIFiles\\SoundEffects\\back_music2.mp3").toURI().toString());
                    mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.play();
                    try {
                        Thread.sleep(120000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        AccountController.loggedInUser = new Guest();
    }

    public static void importData(){
        importDataEach(Person.allPersons,"src/main/resources/database/accounts.txt");
        importDataEach(BossController.getAllDiscount(),"src/main/resources/database/discounts.txt");
        importDataEach(RequestController.getAllRequest(), "src/main/resources/database/requests.txt");
        importDataEach(Category.getAllCategories(), "src/main/resources/database/categories.txt");
        importDataEach(Good.confirmedGoods, "src/main/resources/database/confirmedGoods.txt");
        importDataEach(Good.getAllGoods(), "src/main/resources/database/allGoods.txt");    }

    public static void exportData(){
        for (Person person : Person.allPersons) {
            System.out.println(person);
        }
        exportDataEach(Person.allPersons,"src/main/resources/database/accounts.txt");
        exportDataEach(BossController.getAllDiscount(),"src/main/resources/database/discounts.txt");
        exportDataEach(RequestController.getAllRequest(), "src/main/resources/database/requests.txt");
        exportDataEach(Category.getAllCategories(), "src/main/resources/database/categories.txt");
        exportDataEach(Good.confirmedGoods, "src/main/resources/database/confirmedGoods.txt");
        exportDataEach(Good.getAllGoods(), "src/main/resources/database/allGoods.txt");
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
//    public static  void backSound(boolean x){
//        Media sound =null;
//        if (date!=null){
//            if ((new Date()).getTime()-date.getTime()<10000) return;
//        }
//        if (x){
//            sound = new Media(new File("src\\main\\resources\\GUIFiles\\SoundEffects\\back_music1.mp3").toURI().toString());
//        }else{
//            sound = new Media(new File("src\\main\\resources\\GUIFiles\\SoundEffects\\back_music2.mp3").toURI().toString());
//        }
//        date = new Date();
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();
//    }
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
        mediaPlayer.play();
    }

}
