package Ex1;

import java.util.Objects;
import java.util.Scanner;

enum MeasurementUnit {
    METERS("m"),
    CENTIMETERS("cm"),
    MILLIMETERS("mm");

    private final String unitSuffix;

    MeasurementUnit(String unitSuffix) {
        this.unitSuffix = unitSuffix;
    }

    public boolean isCorrectUnit(String measurement) {
        return measurement.endsWith(unitSuffix);
    }

    public static boolean isAnyUnitCorrect(String measurement) {
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            if (unit.isCorrectUnit(measurement)) {
                return true;
            }
        }
        return false;
    }
}


class Separate {
    public static String[] valueAndUnit(String measurement) {
        String[] parts = measurement.split(" ");

        String numericValue;
        String unit;

        if (parts.length == 2) {
            numericValue = parts[0];
            unit = parts[1];
        } else {
            char lastCharacter = measurement.charAt(measurement.length() - 1);
            char secondLastCharacter = measurement.charAt(measurement.length() - 2);

            if (Character.isDigit(secondLastCharacter)) {
                // The second last character is a digit, so assume it's part of the numeric value
                numericValue = measurement.substring(0, measurement.length() - 1);
                unit = lastCharacter + "";
            } else {
                // The second last character is not a digit, so it's part of the unit
                numericValue = measurement.substring(0, measurement.length() - 2);
                unit = measurement.substring(measurement.length() - 2);
            }
        }

        return new String[]{numericValue, unit};
    }
}


class convert{
    public static float intoMeters (String unit){
        float factor = 0f;
        if (Objects.equals(unit,"m")){
            factor = 1f;
        } else if (Objects.equals(unit, "cm")) {
            factor = 0.01f;
        } else if (Objects.equals(unit, "mm")) {
            factor = 0.001f;
        }
        return factor;
    }
}

public class Measurements_trans_calculation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a width : ");
        String width = in.nextLine();

        System.out.print("Enter a lenght : ");
        String lenght = in.nextLine();

        if (MeasurementUnit.isAnyUnitCorrect(width) && MeasurementUnit.isAnyUnitCorrect(lenght)){
            System.out.println("Both units are correct");
            String[] valueAndUnitLenght = Separate.valueAndUnit(lenght);
            String[] valueAndUnitWidth = Separate.valueAndUnit(width);
            float lengthFactor1 = Float.parseFloat(valueAndUnitLenght[0]);
            float widthFactor1 = Float.parseFloat(valueAndUnitWidth[0]);
            float lengthFactorUnit = convert.intoMeters(valueAndUnitLenght[1]);
            float widthFactorUnit = convert.intoMeters(valueAndUnitWidth[1]);
            float result = lengthFactor1 * widthFactor1 * lengthFactorUnit * widthFactorUnit;
            System.out.println("The result of " + lenght + " time " + width + " is : " + result + " meters square.");
        }
        else{
            System.out.println("Enter measurements with correct units please.");
        }


    }
}


/*
1) Define an enumeration, let's call it Ex1.MeasurementUnit, to represent the valid units of measurement. This enumeration should have three values: METERS, CENTIMETERS, and MILLIMETERS.

2) Add a method to the Ex1.MeasurementUnit enumeration called isCorrectUnit, which takes a measurement string as input and returns a boolean indicating if the unit of measurement is valid or not.

3) Implement the isCorrectUnit method to check if the measurement string ends with "m" for meters, "cm" for centimeters, or "mm" for millimeters. Return true if the unit is valid, and false otherwise.

4) Create a class called SurfaceCalculator that will contain the main method.

5) In the SurfaceCalculator class, prompt the user to input the length and width measurements as strings.

6) Parse the input measurements and extract the numeric values along with the unit of measurement.

7) Use the isCorrectUnit method from the Ex1.MeasurementUnit enumeration to validate the units of the measurements.

8) Convert the measurements to meters based on their respective units. For example, if the measurement is in centimeters, divide the numeric value by 100 to get the value in meters.

9) Calculate the surface area using the converted length and width measurements.

10) Display the calculated surface area to the user.

11) Handle any potential exceptions or invalid inputs gracefully, ensuring that the program does not crash.

12) Test the program with various input values, including edge cases, to verify its correctness.
*/