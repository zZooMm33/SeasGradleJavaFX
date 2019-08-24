import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.StaticFields;

import java.io.IOException;

/**
 * Главный класс программы
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/windows/menu/MainWindow.fxml"));
            primaryStage.setTitle(StaticFields.getNameMainWindow());

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
