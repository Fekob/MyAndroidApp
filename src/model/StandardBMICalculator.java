//Implements the standard BMI calculation: weight (kg) / (height (m) ^ 2).
//This is the most common method used internationally to calculate BMI.

package model;
public class StandardBMICalculator implements BMICalculationStrategy {
    @Override
    public double calculate(double weight, double height) {
        return weight / (height * height);
    }
}
