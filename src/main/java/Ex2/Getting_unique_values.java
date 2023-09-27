package Ex2;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CSV{
    public static void read(){
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\willi\\Desktop\\JavaBeCode\\weekTwo\\src\\main\\resources\\effects-of-covid-19-on-trade-at-21-July-2021-provisional.csv"))) {
            reader.readLine(); // Skip header line

            Map<Integer, Set<String>> columnValuesMap = new HashMap<>(); // Create a map to associate index of column to uniques values in this column (store in Set<String>)

            // Process CSV lines using Java Streams
            reader.lines().forEach(line -> {
                String[] values = line.split(","); // for each line we split all data into String[]
                IntStream.range(0, values.length)
                        .forEach(i -> columnValuesMap
                                .computeIfAbsent(i, k -> new HashSet<>())
                                .add(values[i]));
            });

            // Display Unique Values for Each Column
            columnValuesMap.forEach((columnIndex, uniqueValues) -> {
                System.out.println("Unique values in column " + columnIndex + ": " + uniqueValues);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

public class Getting_unique_values {
    public static void main(String[] args) {
        CSV.read();
    }
}


/*
1) Download the csv file about effects-of-covid-19-on-trade and save it in your project directory.

2) Create a new Java project and set up the necessary project structure.

3) Import the required libraries and classes to work with CSV data and streams.

4) Use the streaming API in Java to read the CSV file efficiently. Disregard the first line as it typically contains headers.

5) Identify the unique values for each column in the CSV data. Java provides a collection that can help you save time when finding unique values.

6) List all the unique values for each column, and display the results to the user.

7) Make sure your program handles any potential exceptions or errors gracefully, ensuring that it doesn't crash when dealing with unexpected data.

8) Test your program with the provided CSV file as well as other sample datasets to verify its correctness and performance.
 */