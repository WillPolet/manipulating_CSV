package Ex3;
import com.opencsv.CSVReader;
import java.io.FileReader;
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
            String output = Direction + " " + Year + " " + Date + " " + Weekday + " " + Country + " " + Commodity + " " + Transport_Mode
                    + " " + Measure + " " + Value + " " + Cumulative;
            return output;
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
                    .collect(Collectors.toList());
//            listOfYear2016.forEach(tradeData -> System.out.println(tradeData.print()));
            List<TradeData> listOfYear2016AndTransportSea = tradeDataList.stream()
                    .filter(tradeData -> tradeData.getYear() == 2016)
                    .filter(tradeData -> Objects.equals(tradeData.getTransport_Mode(),"Sea"))
                    .collect(Collectors.toList());
            listOfYear2016AndTransportSea.forEach(tradeData -> System.out.println(tradeData.print()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Filtering {
    public static void main(String[] args) {
        CSV.toObject();
    }
}

/*
1) Make sure you have access to the same CSV file used in the previous mission, which contains data about the effects of COVID-19 on trade.

2) Create a new Java project and set up the necessary project structure.

3) Define a class, let's call it TradeData, that represents the structure of the CSV data. This class should have fields for each column mentioned in the header of the CSV file.

4) Implement a method to read the CSV file and convert each line into an object of the TradeData class. You can use libraries like OpenCSV or Apache Commons CSV to facilitate this process.

5) Filter the data to include only records from the year 2016 where the country value is 'all'.

6) Print the values for both import and export for each day in the filtered data.

7) Test your program with the provided CSV file to ensure it correctly filters and displays the desired information.
 */