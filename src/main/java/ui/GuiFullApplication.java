package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sun.misc.Launcher;

/**
 * Created by fendta on 07.07.14.
 */
public class GuiFullApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize Controller
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/ui/gui/guiFull.fxml"));
        GridPane root = fxmlLoader.load();
        GuiFullController controller = fxmlLoader.getController();

        // Start Scene
        Scene scene = new Scene(root);
        primaryStage.setTitle("Stock Game");
        primaryStage.setScene(scene);
        // controller.setStage(primaryStage);
        primaryStage.show();
    }
}
