
/**
 * Sample Skeleton for 'mainWindow.fxml' Controller Class
 */

package controllers.menu;

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
import utils.PropReader;
import utils.StaticFields;

import java.io.IOException;
import java.net.URISyntaxException;

public class ControllerMainWindow {

    /**
     * Ссылка на дочернее окно настроек
     */
    private ControllerSettingsWindow childrenSettingsWindow;

    public ControllerSettingsWindow getChildrenSettingsWindow() {
        return childrenSettingsWindow;
    }

    public void setChildrenSettingsWindow(ControllerSettingsWindow childrenSettingsWindow) {
        this.childrenSettingsWindow = childrenSettingsWindow;
    }

    public TextArea getTextAreaInfo() {
        return textAreaInfo;
    }

    public void setTextAreaInfo(TextArea textAreaInfo) {
        this.textAreaInfo = textAreaInfo;
    }

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
            Parent rootAbout = FXMLLoader.load(getClass().getResource("/windows/menu/AboutWindow.fxml"));

            windowAbout.setTitle(StaticFields.getNameAboutWindow());

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
        try {
            Stage windowSettings = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/windows/menu/SettingsWindow.fxml"));
            Parent rootAbout = loader.load();
            windowSettings.setTitle(StaticFields.getNameSettingsWindow());
            Scene sceneAbout = new Scene(rootAbout);
            windowSettings.setScene(sceneAbout);
            windowSettings.initModality(Modality.WINDOW_MODAL); // Делаем окно модельным (не работает)
            windowSettings.show();

            setChildrenSettingsWindow(loader.getController());
            getChildrenSettingsWindow().setParent(this);

        } catch (IOException e) {
            textAreaInfo.appendText("Ошибка при открытии окна Properties\n");
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
