
/**
 * Sample Skeleton for 'mainWindow.fxml' Controller Class
 */

package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import storage.ConnectionDataBase;

import java.io.IOException;

public class ControllerMainWindow {

    @FXML // fx:id="textAreaInfo"
    private TextArea textAreaInfo; // Value injected by FXMLLoader

    @FXML // fx:id="buttonConnection"
    private Button buttonConnection; // Value injected by FXMLLoader

    @FXML // fx:id="buttonDisconnect"
    private Button buttonDisconnect; // Value injected by FXMLLoader

    @FXML // fx:id="settings"
    private MenuItem settings; // Value injected by FXMLLoader

    @FXML // fx:id="about"
    private MenuItem about; // Value injected by FXMLLoader

    @FXML // fx:id="exit"
    private MenuItem exit; // Value injected by FXMLLoader

    @FXML
    void buttonConnectionOnClick(ActionEvent event) {

        //textAreaInfo.setText(""); // очистить

        if (ConnectionDataBase.getConnection() != null){
            textAreaInfo.appendText("Успешно подключено к БД\n");
        }
        else{
            textAreaInfo.appendText("Ошибка при подключении к БД\n");
        }
    }

    @FXML
    void buttonDisconnectOnClick(ActionEvent event) {

        //textAreaInfo.setText(""); // очистить

        if (ConnectionDataBase.disconnect()){
            textAreaInfo.appendText("Соединение с сервером баз данных разорвано\n");
        }
        else {
            textAreaInfo.appendText("Ошибка.\n Соединение с сервером баз данных не было разорвано\n");
        }

    }

    @FXML
    void aboutOnClick(ActionEvent event) {
        try {
            Stage windowAbout = new Stage();
            Parent rootAbout = FXMLLoader.load(getClass().getResource("../windows/AboutWindow.fxml"));

            windowAbout.setTitle("About");

            Scene sceneAbout = new Scene(rootAbout);
            windowAbout.setScene(sceneAbout);
            windowAbout.initModality(Modality.WINDOW_MODAL); // Делаем окно модельным (не работает)
            windowAbout.show();

        } catch (IOException e) {
            textAreaInfo.appendText("Ошибка при открытии окна About\n");
            e.printStackTrace();
        }
    }

    @FXML
    void exitOnClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void settingsOnClick(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
