package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;


public class BMIRecordDAOImpl implements BMIRecordDAO {

    //Adds a new record to the bmi_data table in the database.
    @Override public void addBMIRecord(BMIRecord record) {
        String sql = "INSERT INTO bmi_data (bmi, time) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, record.getBmi());
            ps.setString(2, record.getTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Failed to add BMI record: " + e.getMessage());
        }
    }

//Returns all BMI records stored in the database.
    @Override public ObservableList<BMIRecord> getAllBMIRecords() {
        ObservableList<BMIRecord> records = FXCollections.observableArrayList();
        String sql = "SELECT bmi, time FROM bmi_data ORDER BY time DESC";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                String bmi = rs.getString("bmi");
                String time  = rs.getString("time");
                records.add(new BMIRecord(bmi, time));
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to fetch BMI records: " + e.getMessage());
        }
        return records;
    }

//Delete a specific BMI record from the database.
    @Override public void deleteBMIRecord(BMIRecord record) {
        String sql = "DELETE FROM bmi_data WHERE bmi = ? AND time = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, record.getBmi());
            ps.setString(2, record.getTime());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ BMI record deleted from database.");
            } else {
                System.out.println("⚠️ No matching BMI record found to delete.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to delete BMI record: " + e.getMessage());
        }
    }
}
