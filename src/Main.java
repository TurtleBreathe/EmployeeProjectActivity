import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "textFile.csv";
        List<CSVObjects> csvData = CSVParser.parseCSV(filePath);

        for (CSVObjects record : csvData) {
            System.out.println(record);
        }
    }
}