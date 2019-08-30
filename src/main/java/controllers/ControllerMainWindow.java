
/**
 * Sample Skeleton for 'mainWindow.fxml' Controller Class
 */

package controllers;

import controllers.menu.ControllerSettingsWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import storage.ConnectionDataBase;
import storage.CreateTables;
import storage.Oceans.Oceans;
import storage.StorageSingleton;
import utils.PropReader;
import utils.StaticFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ControllerMainWindow {

    /**
     * Список для таблицы Oceans
     */
    private final ObservableList<Oceans> dataOceans = FXCollections.observableArrayList();

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

    @FXML // fx:id="anchorPane"
    private AnchorPane anchorPane; // Value injected by FXMLLoader

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

    @FXML // fx:id="buttonAddSeas"
    private MenuItem buttonAddSeas; // Value injected by FXMLLoader

    @FXML // fx:id="buttonChangeSeas"
    private MenuItem buttonChangeSeas; // Value injected by FXMLLoader

    @FXML // fx:id="buttonDeleteSeas"
    private MenuItem buttonDeleteSeas; // Value injected by FXMLLoader

    @FXML // fx:id="buttonAddOceans"
    private MenuItem buttonAddOceans; // Value injected by FXMLLoader

    @FXML // fx:id="buttonChangeOceans"
    private MenuItem buttonChangeOceans; // Value injected by FXMLLoader

    @FXML // fx:id="buttonDeleteOceans"
    private MenuItem buttonDeleteOceans; // Value injected by FXMLLoader

    @FXML // fx:id="buttonUpdateOceans"
    private MenuItem buttonUpdateTables; // Value injected by FXMLLoader

    @FXML // fx:id="tbSeas"
    private TableView<?> tbSeas; // Value injected by FXMLLoader

    @FXML // fx:id="tbOceans"
    private TableView<Oceans> tbOceans; // Value injected by FXMLLoader

    @FXML // fx:id="tcSeasId"
    private TableColumn<?, ?> tcSeasId; // Value injected by FXMLLoader

    @FXML // fx:id="tcSeasName"
    private TableColumn<?, ?> tcSeasName; // Value injected by FXMLLoader

    @FXML // fx:id="tcSeasSquare"
    private TableColumn<?, ?> tcSeasSquare; // Value injected by FXMLLoader

    @FXML // fx:id="tcSeasMaxDepth"
    private TableColumn<?, ?> tcSeasMaxDepth; // Value injected by FXMLLoader

    @FXML // fx:id="tcSeasOcean"
    private TableColumn<?, ?> tcSeasOcean; // Value injected by FXMLLoader

    @FXML // fx:id="tcOceansId"
    private TableColumn<Oceans, Integer> tcOceansId; // Value injected by FXMLLoader

    @FXML // fx:id="tcOceansName"
    private TableColumn<Oceans, String> tcOceansName; // Value injected by FXMLLoader

    @FXML // fx:id="tcOceansDescription"
    private TableColumn<Oceans, String> tcOceansDescription; // Value injected by FXMLLoader

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
            new Alert(Alert.AlertType.ERROR, "Ошибка при открытии окна About").show();
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
            new Alert(Alert.AlertType.ERROR, "Ошибка при открытии окна Properties").show();
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
            new Alert(Alert.AlertType.ERROR, "Ошибка при открытии окна Create DataBase").show();
            e.printStackTrace();
        }
    }

    @FXML
    void createTablesOnClick(ActionEvent event) {
        if (CreateTables.Create()){
            new Alert(Alert.AlertType.INFORMATION, "Таблицы были успешно созданы.").show();
        }
        else
            new Alert(Alert.AlertType.ERROR, "Ошибка при создании таблиц в БД.").show();
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
    void buttonAddOceansOnClick(ActionEvent event) {

        Oceans ocean = Oceans.newBuilder()
                .setName("testName")
                .setDescription("testDesc")
                .Build();

        if (StorageSingleton.getSingletonOceans().addOceans(ocean))
        {
            dataOceans.clear();
            dataOceans.addAll(StorageSingleton.getSingletonOceans().getOceans());

            new Alert(Alert.AlertType.INFORMATION, "Океан успешно добавлен.").show();
        }
        else
            new Alert(Alert.AlertType.ERROR, "Ошибка при добавлении океана.").show();
    }

    @FXML
    void buttonChangeOceansOnClick(ActionEvent event) {

    }

    @FXML
    void buttonDeleteOceansOnClick(ActionEvent event) {

        for (Oceans ocean: tbOceans.getSelectionModel().getSelectedItems()){
            if (StorageSingleton.getSingletonOceans().deleteOceans(ocean.getId())){
                new Alert(Alert.AlertType.ERROR, "Ошибка при удалении.").show();
            }
            else {
                dataOceans.clear();
                dataOceans.addAll(StorageSingleton.getSingletonOceans().getOceans());

                new Alert(Alert.AlertType.INFORMATION, "Океан успешно удален.").show();
            }
            break;
        }
    }

    @FXML
    void buttonAddSeasOnClick(ActionEvent event) {

    }

    @FXML
    void buttonChangeSeasOnClick(ActionEvent event) {

    }

    @FXML
    void buttonDeleteSeasOnClick(ActionEvent event) {

    }

    @FXML
    void buttonUpdateTablesOnClick(ActionEvent event) {
        dataOceans.clear();
        dataOceans.addAll(StorageSingleton.getSingletonOceans().getOceans());
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        tcOceansId.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        tcOceansName.setCellValueFactory(cell -> cell.getValue().nameProperty());
        tcOceansDescription.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
        tbOceans.setItems(dataOceans);


        // Обновляем таблицы
        if (ConnectionDataBase.getConnection() != null){
            dataOceans.clear();
            dataOceans.addAll(StorageSingleton.getSingletonOceans().getOceans());
        }
    }
}
