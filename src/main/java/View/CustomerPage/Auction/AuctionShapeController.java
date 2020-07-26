package View.CustomerPage.Auction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AuctionShapeController {

    public AnchorPane myPabe;
    @FXML
    private Label sellerName;

    @FXML
    private Label goodName;

    @FXML
    private ImageView imagV;

    public  void fix(String seller, String good,Image image) {
        this.sellerName.setText(seller);
        this.goodName.setText(good);
        imagV.setImage(image);
    }
}
