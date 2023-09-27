package Ex7;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

class MonthExtractor {
    public static Month extractMonth(String dateString) {
        // Define the date format to match your input format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Parse the input string into a LocalDate object
            LocalDate date = LocalDate.parse(dateString, formatter);

            // Extract the month from the LocalDate
            return date.getMonth();
        } catch (Exception e) {
            // Handle parsing exceptions (e.g., invalid format)
            throw new IllegalArgumentException("Invalid date format or date: " + dateString, e);
        }
    }

//    public static void main(String[] args) {
//        String dateStr = "15/09/2023"; // Example date string in "dd/mm/yyyy" format
//        Month month = extractMonth(dateStr);
//        System.out.println("Month: " + month); // Output the extracted month
//    }
}

class TradeData {
    private String Direction;
    private int Year;
    private String Date;
    private String Weekday;
    private String Country;
    private String Commodity;
    private String Transport_Mode;
    private String Measure;
    private long Value;
    private long Cumulative;

    public TradeData(String Direction, int Year, String Date, String Weekday, String Country,
                     String Commodity, String Transport_Mode, String Measure, long Value, long Cumulative) {
        this.Direction = Direction;
        this.Year = Year;
        this.Date = Date;
        this.Weekday = Weekday;
        this.Country = Country;
        this.Commodity = Commodity;
        this.Transport_Mode = Transport_Mode;
        this.Measure = Measure;
        this.Value = Value;
        this.Cumulative = Cumulative;
    }

    public String print(){
        return Direction + " " + Year + " " + Date + " " + Weekday + " " + Country + " " + Commodity + " " + Transport_Mode
                + " " + Measure + " " + Value + " " + Cumulative;
    }
    public String getDate() {
        return Date;
    }

    public int getYear() {
        return Year;
    }

    public String getCommodity() {
        return Commodity;
    }

    public String getCountry() {
        return Country;
    }

    public String getDirection() {
        return Direction;
    }

    public long getCumulative() {
        return Cumulative;
    }

    public long getValue() {
        return Value;
    }

    public String getMeasure() {
        return Measure;
    }

    public String getTransport_Mode() {
        return Transport_Mode;
    }

    public String getWeekday() {
        return Weekday;
    }

    public void setMeasure(String Measure){
        this.Measure = Measure;
    }

    public void setValue(long Value){
        this.Value = Value;
    }
}

class CSV{
    public static void toObject (){
        String csvFilePath = "C:\\Users\\willi\\Desktop\\JavaBeCode\\weekTwo\\src\\main\\resources\\effects-of-covid-19-on-trade-at-21-July-2021-provisional.csv";
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] headers = reader.readNext(); // Read the header line

            List<Ex7.TradeData> tradeDataList = new ArrayList<>();


            String[] line;
            while ((line = reader.readNext()) != null) {
                Ex7.TradeData tradeData = new Ex7.TradeData(
                        line[0], Integer.parseInt(line[1]), line[2],
                        line[3], line[4], line[5], line[6], line[7],
                        Long.parseLong(line[8]), Long.parseLong(line[9])
                );
                tradeDataList.add(tradeData);
            }

            Map<Month,Double> result2019 = tradeDataList.stream()
                    .filter(tradeData -> tradeData.getYear() == 2019)
                    .filter(tradeData -> tradeData.getCountry().contains("European Union"))
                    .collect(Collectors.groupingBy(
                            tradeData -> MonthExtractor.extractMonth(tradeData.getDate()),
                            Collectors.summingDouble(TradeData::getValue)
                    ));

              Map<Month,Double> result2020 = tradeDataList.stream()
                      .filter(tradeData -> tradeData.getYear() == 2020)
                      .filter(tradeData -> tradeData.getCountry().contains("European Union"))
                    .collect(Collectors.groupingBy(
                            tradeData -> MonthExtractor.extractMonth(tradeData.getDate()),
                            Collectors.summingDouble(TradeData::getValue)
                    ));

            result2019.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey()) // Sort by month
                    .forEach(entry -> {
                        Month month = entry.getKey();
                        Double value = entry.getValue();
                        System.out.println(month + " 2019, Value: " + value);
                    });

            result2020.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey()) // Sort by month
                    .forEach(entry -> {
                        Month month = entry.getKey();
                        Double value = entry.getValue();
                        System.out.println(month + " 2020, Value: " + value);
                    });
            //System.out.println(listOfYear2019.getClass().getSimpleName()); // ArrayList
//            return void;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return void;
    }
}

public class Check_the_difference_of_trade {
    public static void main(String[] args) {
        CSV.toObject();
    }
}
