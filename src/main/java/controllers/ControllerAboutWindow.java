/**
 * Sample Skeleton for 'AboutWindow.fxml' Controller Class
 */

package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAboutWindow {

    @FXML // fx:id="buttonOk"
    private Button buttonOk; // Value injected by FXMLLoader

    @FXML
    void buttonOkOnClick(ActionEvent event) {
        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
    }

}
