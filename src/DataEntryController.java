import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.BMICalculationStrategy;
import model.StandardBMICalculator;
import java.io.IOException;



public class DataEntryController {

    @FXML private TextField heightField;
    @FXML private TextField weightField;
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private Button calculateButton;
    @FXML private Button backButton;
    @FXML private ToggleGroup genderGroup;

    @FXML public void initialize() {
        //Preparing a gender options group
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);

        calculateButton.setOnAction(event -> calculateAndNavigate());
        backButton.setOnAction(event -> {
            //Return to the About BMI page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/AboutBMI.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    //Calculates BMI and goes to the result page
    private void calculateAndNavigate() {
        try {
            double height = Double.parseDouble(heightField.getText()) / 100.0; // convert cm to m
            double weight = Double.parseDouble(weightField.getText());

            //Make sure the data entry is correct
            if (height <= 0 || weight <= 0) {
                showAlert("Please enter valid height and weight.");
                return;
            }

            BMICalculationStrategy strategy = new StandardBMICalculator();
            double bmi = strategy.calculate(weight, height);
            String status = getHealthStatus(bmi);

            // Download the results page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/Result.fxml"));
            Parent root = loader.load();

            // Passing data to the new controller
            ResultPage controller = loader.getController();
            controller.setBMIResult(bmi, status);

            // Open new page
            Stage stage = new Stage();
            stage.setTitle("BMI Result");
            stage.setScene(new Scene(root));
            stage.show();

            //Close the current page
            Stage currentStage = (Stage) calculateButton.getScene().getWindow();
            currentStage.close();

        } catch (NumberFormatException | IOException e) {
            showAlert("Invalid input or error loading the result page.");
            e.printStackTrace();
        }
    }

    //Returns health status based on BMI value
    private String getHealthStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }

    //Displays an error message to the user.
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
