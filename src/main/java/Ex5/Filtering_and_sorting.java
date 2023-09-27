package Ex5;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
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

class ImportComparator implements java.util.Comparator<TradeData> {
    @Override
    public int compare(TradeData a, TradeData b) {
        long aValue = a.getValue();
        long bValue = b.getValue();

        if (aValue < bValue) {
            return -1;
        } else if (aValue > bValue) {
            return 1;
        } else {
            return 0;
        }
    }
}


class CSV{
    public static void toObject (){
        String csvFilePath = "C:\\Users\\willi\\Desktop\\JavaBeCode\\weekTwo\\src\\main\\resources\\effects-of-covid-19-on-trade-at-21-July-2021-provisional.csv";
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] headers = reader.readNext(); // Read the header line

            List<Ex5.TradeData> tradeDataList = new ArrayList<>();


            String[] line;
            while ((line = reader.readNext()) != null) {
                Ex5.TradeData tradeData = new Ex5.TradeData(
                        line[0], Integer.parseInt(line[1]), line[2],
                        line[3], line[4], line[5], line[6], line[7],
                        Long.parseLong(line[8]), Long.parseLong(line[9])
                );
                tradeDataList.add(tradeData);
            }


            List<Ex5.TradeData> listOfYear2018 = tradeDataList.stream()
                    .filter(tradeData -> tradeData.getYear() == 2018)
                    .filter(tradeData -> Objects.equals(tradeData.getTransport_Mode(), "All"))
                    .filter(tradeData -> Objects.equals(tradeData.getCountry(), "All"))
                    .filter(tradeData -> Objects.equals(tradeData.getCommodity(), "All"))
                    .collect(Collectors.toList());

            //System.out.println(listOfYear2018.getClass().getSimpleName()); // ArrayList
            Collections.sort(listOfYear2018, new ImportComparator());
            listOfYear2018.forEach(tradeData -> {
                System.out.println(tradeData.print());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class Filtering_and_sorting {
    public static void main(String[] args) {
        CSV.toObject();
    }
}
