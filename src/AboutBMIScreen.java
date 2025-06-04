import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AboutBMIScreen {
    @FXML private Button calculateBMIButton;
    @FXML private Button backButton;

    @FXML private void initialize() {
        calculateBMIButton.setOnAction(event -> {
            //Go to the data entry page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/DataEntry.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) calculateBMIButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//Return to the Login page
        backButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/Login.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}