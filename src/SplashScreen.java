import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

public class SplashScreen {

    @FXML private Button Start;
    @FXML private void initialize() {
        // When the Start button is clicked, navigate to the Login screen
        Start.setOnAction(this::goToDataEntry);
    }

    private void goToDataEntry(ActionEvent event) {
        try {
            // Load the Login.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Start.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            // Print the error if loading fails
            e.printStackTrace();
        }
    }
}
