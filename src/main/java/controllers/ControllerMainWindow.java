
/**
 * Sample Skeleton for 'mainWindow.fxml' Controller Class
 */

package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import storage.ConnectionDataBase;

public class ControllerMainWindow {


    @FXML // fx:id="textAreaInfo"
    private TextArea textAreaInfo; // Value injected by FXMLLoader

    @FXML // fx:id="buttonConnection"
    private Button buttonConnection; // Value injected by FXMLLoader

    @FXML // fx:id="buttonDisconnect"
    private Button buttonDisconnect;

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

        ConnectionDataBase.disconnect();
        textAreaInfo.appendText("Соединение с сервером баз данных разорвано\n");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
