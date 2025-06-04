import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import model.User;
import java.io.IOException;
import java.util.regex.Pattern;


public class SignUpController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button createButton;
    @FXML private Button backButton;
    @FXML private Label messageLabel;

    @FXML public void initialize() {
        createButton.setOnAction(e -> handleCreateAccount());
        backButton.setOnAction(e -> handleBack());
    }


    //Create a new account after verifying the data
    private void handleCreateAccount() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("Please fill all fields.");
            return;
        }

        if(!isValidEmail(email)) {
            messageLabel.setText("Invalid email format.");
            return;
        }

        if(!password.equals(confirmPassword)) {
            messageLabel.setText("Passwords do not match.");
            return;
        }

        if (!isStrongPassword(password)) {
            passwordField.clear();
            messageLabel.setText("Weak password! Use A-Z, a-z, 0-9, and symbols.");
            passwordField.setStyle("-fx-prompt-text-fill: red;");
            return;
        }

        User newUser = new User(name, email, password);

        boolean saved = saveUser(newUser);

        if(saved) {
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Account created successfully!");
            clearFields();

            //Go to the BMI information page if all the information is correct
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/AboutBMI.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            messageLabel.setText("Error saving account. Try again.");
        }
    }

// Return to the Login page
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        // Check email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
    private boolean isStrongPassword(String password) {

        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=<>?{}\\[\\]~]).{8,}$";
        return password.matches(pattern);
    }

    private boolean saveUser(User user) {
        // الكود الحقيقي هنا لحفظ بيانات المستخدم في ملف أو قاعدة بيانات
        // دلوقتي مجرد مثال يعيد true
        return true;
    }
    // Completely erased after success
    private void clearFields() {
        nameField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }
}
