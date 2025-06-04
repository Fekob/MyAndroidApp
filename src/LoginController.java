import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import java.io.IOException;


  public class LoginController {


      @FXML private Label messageLabel;
      @FXML private TextField emailField;
      @FXML private PasswordField passwordField;
      @FXML private Button loginButton;
      @FXML private Button signUpLink;
      @FXML private Button backButton;
      @FXML public void initialize() {
          //Make sure the password and email are present or not
          loginButton.setOnAction(event -> {
              String email = emailField.getText();
              String password = passwordField.getText();

              if (email.isEmpty() || password.isEmpty()) {
                  messageLabel.setText("Please fill all fields.");
                  return;
              }

              if (!isValidEmail(email)) {
                  messageLabel.setText("Invalid email format.");
                  return;
              }

              if (!isStrongPassword(password)) {
                  passwordField.clear();
                  messageLabel.setText("Weak password! Use A-Z, a-z, 0-9, and symbols.");
                  passwordField.setStyle("-fx-prompt-text-fill: red;");
                  return;
              }


              User newUser = new User(email, password);
              boolean saved = saveUser(newUser);

              if (saved) {
                  messageLabel.setStyle("-fx-text-fill: green;");
                  messageLabel.setText("Account created successfully!");
                  clearFields();

                  //If all is true, go to the About BMI page.
                  try {
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/AboutBMI.fxml"));
                      Parent root = loader.load();

                      Stage stage = (Stage) loginButton.getScene().getWindow();
                      stage.setScene(new Scene(root));
                  } catch (IOException e) {
                      e.printStackTrace();
                  }


              } else {
                  messageLabel.setText("Error saving account. Try again.");
              }
          });

          signUpLink.setOnAction(event -> {
              //Go button to register a new account
              try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/SignUp.fxml"));
                  Parent root = loader.load();
                  Stage stage = (Stage) signUpLink.getScene().getWindow();
                  stage.setScene(new Scene(root));
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
          });

          backButton.setOnAction(event -> {
              //Back to Home button
              try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/Splash.fxml"));
                  Parent root = loader.load();
                  Stage stage = (Stage) backButton.getScene().getWindow();
                  stage.setScene(new Scene(root));
              } catch (IOException e) {
                  e.printStackTrace();
              }
          });
      }

      //Verifies the user's email address
      private boolean isValidEmail(String email) {
          return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
      }
      private boolean isStrongPassword(String password) {

          String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=<>?{}\\[\\]~]).{8,}$";
          return password.matches(pattern);
      }


      //Saves user data (currently only prints)
      private boolean saveUser(User user) {
          System.out.println("Saving user: " + user.getEmail());
          return true;
      }

      //// Completely erased after success
      private void clearFields() {
          emailField.clear();
          passwordField.clear();
      }
  }

