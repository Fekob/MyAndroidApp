package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Connect to a SQLite database.

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:C:/Users/mtho2/IdeaProjects/BMICalculatorApp/src/model/bmi_data.db";
    //Establishes a connection to the SQLite database.
    public static Connection getConnection() throws SQLException {
        System.out.println("Connecting to DB at: " + URL);
        return DriverManager.getConnection(URL);
    }
}
