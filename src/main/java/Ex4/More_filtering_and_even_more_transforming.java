package Ex4;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.ObjectStreamException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

            List<TradeData> tradeDataList = new ArrayList<>();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            String[] line;
            while ((line = reader.readNext()) != null) {
                TradeData tradeData = new TradeData(
                        line[0], Integer.parseInt(line[1]), line[2],
                        line[3], line[4], line[5], line[6], line[7],
                        Long.parseLong(line[8]), Long.parseLong(line[9])
                );
                tradeDataList.add(tradeData);
            }
//            just to check if there's a match
//            if(tradeDataList.stream().anyMatch(tradeData -> (tradeData.getYear() == 2016))){ // print ok once, bc any math yes there's
//            }

            List<TradeData> listOfYear2016 = tradeDataList.stream()
                    .filter(tradeData -> tradeData.getYear() == 2016)
                    .filter(tradeData -> Objects.equals(tradeData.getTransport_Mode(), "Sea"))
                    .collect(Collectors.toList());

            listOfYear2016.forEach(tradeData -> {
                if (Objects.equals(tradeData.getMeasure(), "$")) {
                    tradeData.setMeasure("€");
                    tradeData.setValue((long) (tradeData.getValue()*0.85));
                }
                System.out.println(tradeData.print());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class More_filtering_and_even_more_transforming {
    public static void main(String[] args) {
        CSV.toObject();

    }
}

/*
1) Make sure you have access to the same CSV file used in the previous missions, which contains data about the effects of COVID-19 on trade.

2) Create a new Java project and set up the necessary project structure.

3) Define a class, let's call it TradeData, that represents the structure of the CSV data. This class should have fields for each column in the CSV file that contains the export and import values.

4) Implement a method to read the CSV file and convert each line into an object of the TradeData class.

5) Filter the data to include only records from the year 2016.

6) For each record, check if the value for export and import is expressed in dollars. If it is, convert the value to euros using the exchange rate of 0.85.

7) Print the values for export and import, both in dollars and euros, for each day in the year 2016.
 */
