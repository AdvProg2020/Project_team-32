package View;

import Server.Controller.AccountController;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogoutPageController implements Initializable {
    public ImageView imageView;
    private static final Image IMAGE = new Image("GUIFiles\\Customer_page_bg\\The_Horse_in_Motion.jpg");

    private static final int COLUMNS  =   4;
    private static final int COUNT    =  10;
    private static final int OFFSET_X =  18;
    private static final int OFFSET_Y =  25;
    private static final int WIDTH    = 374;
    private static final int HEIGHT   = 243;

    public void signOut(ActionEvent actionEvent) {

        AccountController.logout();
        try {
            URL url = new File("src\\main\\resources\\GUIFiles\\main-page.fxml").toURI().toURL();
            Parent parent = FXMLLoader.load(url);
            Client.primaryStage.setScene(new Scene(parent));
        } catch (IOException e) {
            System.err.println("error loading files.");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(IMAGE);
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

        final Animation animation = new SpriteAnimation(
                imageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();


    }
    class SpriteAnimation extends Transition {

        private final ImageView imageView;
        private final int count;
        private final int columns;
        private final int offsetX;
        private final int offsetY;
        private final int width;
        private final int height;

        private int lastIndex;

        public SpriteAnimation(
                ImageView imageView,
                Duration duration,
                int count,   int columns,
                int offsetX, int offsetY,
                int width,   int height) {
            this.imageView = imageView;
            this.count     = count;
            this.columns   = columns;
            this.offsetX   = offsetX;
            this.offsetY   = offsetY;
            this.width     = width;
            this.height    = height;
            setCycleDuration(duration);
            setInterpolator(Interpolator.LINEAR);
        }

        protected void interpolate(double k) {
            final int index = Math.min((int) Math.floor(k * count), count - 1);
            if (index != lastIndex) {
                final int x = (index % columns) * width  + offsetX;
                final int y = (index / columns) * height + offsetY;
                imageView.setViewport(new Rectangle2D(x, y, width, height));
                lastIndex = index;
            }
        }
    }
}
