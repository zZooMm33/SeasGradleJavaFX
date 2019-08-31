/**
 * Sample Skeleton for 'AddChangeOceanWindow.fxml' Controller Class
 */

package controllers.storage;

import controllers.ControllerMainWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import storage.ConnectionDataBase;
import storage.Oceans.Oceans;
import storage.StorageSingleton;

public class ControllerAddChangeOceanWindow {

    /**
     * Main Window Controller
     */
    private ControllerMainWindow controllerMainWindow = null;

    /**
     * Океан который надо будет изменить
     */
    private Oceans oceanChange = null;

    /**
     * Сохранение Main Window Controller
     * @param controllerMainWindow контроллер главной формы
     */
    public void setControllerMainWindow(ControllerMainWindow controllerMainWindow) {
        this.controllerMainWindow = controllerMainWindow;
    }

    /**
     * Сохранение океана
     * @param oceanChange океан из главной формы
     */
    public void setOceanChange(Oceans oceanChange) {
        this.oceanChange = oceanChange;

        if (oceanChange != null){
            labelAddChangeOcean.setText("Change ocean");
            buttonAddOcean.setText("Change");
            buttonAddOcean.setLayoutX(134); // кнопка стала больше

            textFieldNameOcean.setText(oceanChange.getName());
            textAreaDescriptionOcean.setText(oceanChange.getDescription());
        }
        else {
            // строка не выбрана
            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
            new Alert(Alert.AlertType.ERROR, "Ошибка при изменении океана.").show();
        }
    }

    @FXML // fx:id="labelAddChangeOcean"
    private Label labelAddChangeOcean; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldNameOcean"
    private TextField textFieldNameOcean; // Value injected by FXMLLoader

    @FXML // fx:id="textAreaDescriptionOcean"
    private TextArea textAreaDescriptionOcean; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCancel"
    private Button buttonCancel; // Value injected by FXMLLoader

    @FXML // fx:id="buttonAddOcean"
    private Button buttonAddOcean; // Value injected by FXMLLoader

    @FXML
    void buttonAddOceanOnClick(ActionEvent event) {

        if (textFieldNameOcean.getText().equals(""))
        {
            new Alert(Alert.AlertType.ERROR, "Введите имя.").show();
            return;
        }

        if (oceanChange != null){
            oceanChange.setName(textFieldNameOcean.getText());
            oceanChange.setDescription(textAreaDescriptionOcean.getText());

            if (StorageSingleton.getSingletonOceans().changeOceans(oceanChange))
            {
                controllerMainWindow.getDataOceans().clear();
                controllerMainWindow.getDataOceans().addAll(StorageSingleton.getSingletonOceans().getOceans());

                new Alert(Alert.AlertType.INFORMATION, "Океан успешно изменен.").show();
            }
            else
                new Alert(Alert.AlertType.ERROR, "Ошибка при изменении океана.").show();
        }
        else {
            Oceans ocean = Oceans.newBuilder()
                    .setName(textFieldNameOcean.getText())
                    .setDescription(textAreaDescriptionOcean.getText())
                    .Build();

            if (StorageSingleton.getSingletonOceans().addOceans(ocean))
            {
                controllerMainWindow.getDataOceans().clear();
                controllerMainWindow.getDataOceans().addAll(StorageSingleton.getSingletonOceans().getOceans());

                new Alert(Alert.AlertType.INFORMATION, "Океан успешно добавлен.").show();
            }
            else
                new Alert(Alert.AlertType.ERROR, "Ошибка при добавлении океана.").show();
        }

        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void buttonCancelOnClick(ActionEvent event) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

}
