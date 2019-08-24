/**
 * Sample Skeleton for 'SettingsWindow.fxml' Controller Class
 */

package controllers.menu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.PropReader;

public class ControllerSettingsWindow {

    /**
     * Основное окно
     */
    private ControllerMainWindow parent;

    public ControllerMainWindow getParent() {
        return parent;
    }

    public void setParent(ControllerMainWindow parent) {
        this.parent = parent;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="labelHost"
    private Label labelHost; // Value injected by FXMLLoader

    @FXML // fx:id="labelPass"
    private Label labelPass; // Value injected by FXMLLoader

    @FXML // fx:id="labelUser"
    private Label labelUser; // Value injected by FXMLLoader

    @FXML // fx:id="labelType"
    private Label labelType; // Value injected by FXMLLoader

    @FXML // fx:id="labelSite"
    private Label labelSite; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldHost"
    private TextField textFieldHost; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldUser"
    private TextField textFieldUser; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldPass"
    private TextField textFieldPass; // Value injected by FXMLLoader

    @FXML // fx:id="textAreaSite"
    private TextArea textAreaSite; // Value injected by FXMLLoader

    @FXML // fx:id="comboBoxStorageType"
    private ComboBox comboBoxStorageType; // Value injected by FXMLLoader

    @FXML // fx:id="buttonOk"
    private Button buttonOk; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCancel"
    private Button buttonCancel; // Value injected by FXMLLoader

    @FXML
    void buttonCancelOnClick(ActionEvent event) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void buttonOkOnClick(ActionEvent event) {
        if (!PropReader.setVal("host", textFieldHost.getText())
                || !PropReader.setVal("user", textFieldUser.getText())
                || !PropReader.setVal("pass", textFieldPass.getText())
                || !PropReader.setVal("storageType", comboBoxStorageType.getValue().toString())
                || !PropReader.setVal("siteParse", textAreaSite.getText())){
            parent.getTextAreaInfo().appendText("Ошибка при сохранении настроек.\n");
        }

        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        textFieldHost.setText(PropReader.getVal("host"));
        textFieldUser.setText(PropReader.getVal("user"));
        textFieldPass.setText(PropReader.getVal("pass"));
        textAreaSite.setText(PropReader.getVal("siteParse"));
        comboBoxStorageType.setValue(PropReader.getVal("storageType"));
        comboBoxStorageType.setItems(FXCollections.observableArrayList("databaseSQLite", "databaseH2", ".txt"));
    }
}
