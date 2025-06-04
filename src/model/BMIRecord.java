package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//A single record representation of the Body Mass Index (BMI) containing:
//BMI value
//Date and time

public class BMIRecord {
    private final StringProperty bmi;
    private final StringProperty time;

    public BMIRecord(String bmi, String time) {
        this.bmi = new SimpleStringProperty(bmi);
        this.time = new SimpleStringProperty(time);
    }

    // Getter and Setter for BMI
    public String getBmi() {
        return bmi.get();
    }

    public void setBmi(String value) {
        bmi.set(value);
    }

    public StringProperty bmiProperty() {
        return bmi;
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String value) {
        time.set(value);
    }

    public StringProperty timeProperty() {
        return time;
    }
}
