
/**
 * Sample Skeleton for 'mainWindow.fxml' Controller Class
 */

package controllers;

import controllers.menu.ControllerSettingsWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import storage.ConnectionDataBase;
import storage.CreateTables;
import utils.PropReader;
import utils.StaticFields;

import java.io.IOException;

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

    @FXML // fx:id="anchorPane"
    private AnchorPane anchorPane; // Value injected by FXMLLoader

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

    @FXML // fx:id="createDB"
    private MenuItem createDB; // Value injected by FXMLLoader

    @FXML // fx:id="createTables"
    private MenuItem createTables; // Value injected by FXMLLoader

    @FXML // fx:id="testConnectionDataBase"
    private MenuItem testConnectionDataBase; // Value injected by FXMLLoader

    @FXML
    void buttonConnectionOnClick(ActionEvent event) {

        //textAreaInfo.setText(""); // очистить

        if (ConnectionDataBase.getConnection() != null) {
            textAreaInfo.appendText("Успешно подключено к БД\n");
        } else {
            textAreaInfo.appendText("Ошибка при подключении к БД\n");
        }
    }

    @FXML
    void buttonDisconnectOnClick(ActionEvent event) {

        //textAreaInfo.setText(""); // очистить

        if (ConnectionDataBase.disconnect()) {
            textAreaInfo.appendText("Соединение с сервером баз данных разорвано\n");
        } else {
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
            windowAbout.initModality(Modality.WINDOW_MODAL); // Делаем окно модельным
            windowAbout.initOwner((Stage) anchorPane.getScene().getWindow());
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
            windowSettings.initModality(Modality.WINDOW_MODAL); // Делаем окно модельным
            windowSettings.initOwner((Stage) anchorPane.getScene().getWindow());

            windowSettings.show();

            setChildrenSettingsWindow(loader.getController());
            getChildrenSettingsWindow().setParent(this);

        } catch (IOException e) {
            textAreaInfo.appendText("Ошибка при открытии окна Properties\n");
            e.printStackTrace();
        }
    }

    @FXML
    void createDBOnClick(ActionEvent event) {
        try {
            Stage windowCreateDataBase = new Stage();
            Parent rootAbout = FXMLLoader.load(getClass().getResource("/windows/dataBase/CreateDataBaseWindow.fxml"));

            windowCreateDataBase.setTitle(StaticFields.getNameAboutWindow());

            Scene sceneAbout = new Scene(rootAbout);
            windowCreateDataBase.setScene(sceneAbout);
            windowCreateDataBase.initModality(Modality.WINDOW_MODAL); // Делаем окно модельным
            windowCreateDataBase.initOwner((Stage) anchorPane.getScene().getWindow());
            windowCreateDataBase.show();

        } catch (IOException e) {
            textAreaInfo.appendText("Ошибка при открытии окна Create DataBase\n");
            e.printStackTrace();
        }
    }

    @FXML
    void createTablesOnClick(ActionEvent event) {
        CreateTables.Create();
    }

    @FXML
    void testConnectionDataBaseOnClick(ActionEvent event) {
        if (ConnectionDataBase.getConnection() != null) {
            new Alert(Alert.AlertType.INFORMATION, "Подключение было успешно проверено.\nОшибок выявлено не было.").show();
            ConnectionDataBase.disconnect();


        } else {
            boolean cyrillic = PropReader.getVal("host").chars()
                    .mapToObj(Character.UnicodeBlock::of)
                    .anyMatch(b -> b.equals(Character.UnicodeBlock.CYRILLIC));

            if (cyrillic)
                new Alert(Alert.AlertType.ERROR, "Ошибка при подключении к БД. \nВ строке присутствует Кириллица.").show();
            else
                new Alert(Alert.AlertType.ERROR, "Ошибка при подключении к БД.").show();
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
