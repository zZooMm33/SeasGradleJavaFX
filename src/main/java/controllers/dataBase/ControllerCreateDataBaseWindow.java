package controllers.dataBase;

        import javafx.collections.FXCollections;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.stage.Stage;
        import storage.CreateDataBase;
        import utils.PropReader;
        import utils.StaticFields;

        import java.io.File;

public class ControllerCreateDataBaseWindow {

    @FXML // fx:id="comboBoxStorageType"
    private ComboBox comboBoxStorageType; // Value injected by FXMLLoader

    @FXML // fx:id="checkBoxCreateTables"
    private CheckBox checkBoxCreateTables; // Value injected by FXMLLoader

    @FXML // fx:id="checkBoxSaveSettings"
    private CheckBox checkBoxSaveSettings; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldDataBaseName"
    private TextField textFieldDataBaseName; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCreate"
    private Button buttonCreate; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCancel"
    private Button buttonCancel; // Value injected by FXMLLoader

    @FXML
    void buttonCancelOnClick(ActionEvent event) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void buttonCreateOnClick(ActionEvent event) {

        if (textFieldDataBaseName.getText().length() == 0){
            new Alert(Alert.AlertType.ERROR, "Введите имя новой базы данных.").show();
        }
        else if (textFieldDataBaseName.getText().chars()
                .mapToObj(Character.UnicodeBlock::of)
                .anyMatch(b -> b.equals(Character.UnicodeBlock.CYRILLIC))){
            new Alert(Alert.AlertType.ERROR, "Имя базы данных не должно содержать Кириллицу.").show();
        }
        else if (new File(System.getProperty("user.home") + "/" + StaticFields.getNameProgram() + "/db/" + textFieldDataBaseName.getText()).exists()){
            new Alert(Alert.AlertType.ERROR, "База данных с таким именем уже существует.").show();
        }
        else{
            if (CreateDataBase.Create(textFieldDataBaseName.getText(),
                    (String) comboBoxStorageType.getValue(),
                    checkBoxCreateTables.isSelected(),
                    checkBoxSaveSettings.isSelected()))
            {
                Stage stage = (Stage) buttonCancel.getScene().getWindow();
                stage.close();


                String host = null;
                if (((String) comboBoxStorageType.getValue()).equals("databaseSQLite")){
                    host = "jdbc:sqlite:" + System.getProperty("user.home") + "/" + StaticFields.getNameProgram() + "/db/" + textFieldDataBaseName.getText() + "/" + textFieldDataBaseName.getText() + ".db";
                }
                else
                {
                    host = "jdbc:h2:" + System.getProperty("user.home") + "/" + StaticFields.getNameProgram() + "/db/" + textFieldDataBaseName.getText() + "/" + textFieldDataBaseName.getText();
                }


                new Alert(Alert.AlertType.INFORMATION,
                        "База данных была успешно создана.\n" +
                                "host: " + host + "\n" +
                                "user: " + StaticFields.getUserNameDb() + "\n" +
                                "pass: " + StaticFields.getUserPassDb())
                        .show();
            }
            else
            {
                new Alert(Alert.AlertType.ERROR, "Ошибка при создании базы данных.").show();
            }
        }


    }

    @FXML
    void checkBoxSaveSettingsOnClick(ActionEvent event) {
        // после клика 2 флаг фолс
        if (checkBoxCreateTables.isSelected() && !checkBoxSaveSettings.isSelected()){
            checkBoxCreateTables.setSelected(false);
        }
    }

    @FXML
    void checkBoxCreateTablesOnClick(ActionEvent event) {
        if (checkBoxCreateTables.isSelected() && !checkBoxSaveSettings.isSelected()){
            checkBoxCreateTables.setSelected(false);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        comboBoxStorageType.setValue("databaseSQLite");
        comboBoxStorageType.setItems(FXCollections.observableArrayList("databaseSQLite", "databaseH2"));

        // для проверки isSelected
        checkBoxCreateTables.setSelected(true);
        checkBoxSaveSettings.setSelected(true);
    }

}
