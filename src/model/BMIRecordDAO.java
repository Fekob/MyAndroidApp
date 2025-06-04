package model;

import javafx.collections.ObservableList;

// Interface defining methods to interact with BMI records in the database.
//- Add a BMI record
//- Retrieve all BMI records
//- Delete a BMI recor

public interface BMIRecordDAO {
    void addBMIRecord(BMIRecord record);
    ObservableList<BMIRecord> getAllBMIRecords();
    void deleteBMIRecord(BMIRecord record);
}
