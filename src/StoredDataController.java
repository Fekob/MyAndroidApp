import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.BMIRecord;
import model.BMIRecordDAO;
import model.BMIRecordDAOImpl;
import java.io.IOException;


public class StoredDataController {
    @FXML private TableView<BMIRecord> bmiTable;
    @FXML private TableColumn<BMIRecord, String> bmiColumn;
    @FXML private TableColumn<BMIRecord, String> timeColumn;
    @FXML private Button backButton;
    @FXML private Button deleteButton;

    //Stores the data that appears in the table in the user interface.
    private final ObservableList<BMIRecord> bmiData = FXCollections.observableArrayList();

    //Deals with database (fetch/delete/add BMI records)
    private final BMIRecordDAO bmiRecordDAO = new BMIRecordDAOImpl();


    //Initializes the controller by loading BMI records from the database,setting up the table columns,
    // and configuring button actions.
    @FXML private void initialize() {
        loadBMIData();
        bmiColumn.setCellValueFactory(cellData -> cellData.getValue().bmiProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        bmiTable.setItems(bmiData);
        backButton.setOnAction(event -> {
            //Return to the Result page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlPkg/Result.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteButton.setOnAction(event -> deleteSelectedRecord());
    }

    // Deletes the selected BMI record from both the database and the table view.
    // If no record is selected, the method does nothing
    private void deleteSelectedRecord() {
        BMIRecord selected = bmiTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            bmiRecordDAO.deleteBMIRecord(selected); // استخدام الـ DAO للحذف من قاعدة البيانات
            bmiData.remove(selected); // قم بإزالة السجل من الـ ObservableList لتحديث الـ UI
            System.out.println("BMI record deleted from database.");
        }
    }
// Loads all BMI records from the database into the observable list and displays them in the table view.
    private void loadBMIData() {
        bmiData.clear();
        bmiData.addAll(bmiRecordDAO.getAllBMIRecords()); // استخدام الـ DAO لجلب جميع السجلات
    }
}