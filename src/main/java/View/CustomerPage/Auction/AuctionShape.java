package View.CustomerPage.Auction;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AuctionShape extends  Pane{
    private AuctionShapeController auctionShapeController;
    private String ID;
    public AuctionShape(String name, String seller,String ID ) {
        this.ID =ID;
        URL url = null;
        try {
            url = new File("src\\sample\\ShapeFXML.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(url);

        //set main scene
        try {
            Image image = new Image("sample\\law.png");
            Parent parent = loader.load();
            this.auctionShapeController = loader.getController();
            getChildren().add(parent);
            this.auctionShapeController.fix(seller, name, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




