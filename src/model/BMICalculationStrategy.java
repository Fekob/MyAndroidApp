//Strategic interface for calculating body mass index.

package model;
public interface BMICalculationStrategy {
    double calculate(double weight, double height);
}
