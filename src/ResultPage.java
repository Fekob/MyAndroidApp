import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BMIRecord;
import model.BMIRecordDAO;
import model.BMIRecordDAOImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ResultPage {
    @FXML private Label bmiValueLabel;
    @FXML private Label bmiStatusLabel;
    @FXML private ProgressBar bmiProgressBar;
    @FXML private Button saveButton;
    @FXML private Button backButton;
    @FXML private Button showDataButton;
    private double bmiValue;

    //Displays the BMI value and determines the ProgressBar level based on the value.
    public void setBMIResult(double bmi, String status) {
        this.bmiValue = bmi;
        bmiValueLabel.setText(String.format("Your BMI: %.2f", bmi));
        bmiStatusLabel.setText("Health Status: " + status);
        double progress = normalizeBMIToProgress(bmi);
        bmiProgressBar.setProgress(progress);
    }

    //Convert the BMI value to a value between 0 and 1 to display it in the ProgressBar
    private double normalizeBMIToProgress(double bmi) {
        if (bmi < 10) return 0.0;
        if (bmi > 40) return 1.0;
        return (bmi - 10) / 30.0; // يحول الـ BMI من 10–40 إلى 0–1
    }

    //Setting events for the button when the page loads
    @FXML private void initialize() {
        saveButton.setOnAction(event -> saveBmiData());

        backButton.setOnAction(event -> {
            //Return to the data entry page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/DataEntry.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        });

        showDataButton.setOnAction(event -> {
            //Saves data and displays it on the Stored Data page.
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/StoredData.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) showDataButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        });
    }

    //Save BMI data with current date in database
    @FXML private void saveBmiData() {
        System.out.println("Saving BMI data to database");
        BMIRecordDAO dao = new BMIRecordDAOImpl();
        String bmiStr = String.format("%.2f", bmiValue);
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        BMIRecord record = new BMIRecord(bmiStr, currentTime);
        dao.addBMIRecord(record);
        System.out.println("BMI data saved successfully.");
    }
}