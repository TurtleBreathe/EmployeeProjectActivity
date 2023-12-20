import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public static List<CSVObjects> parseCSV(String filePath) {
        List<CSVObjects> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");
                if (columns.length == 4) {
                    try {
                        int col1 = Integer.parseInt(columns[0].trim());
                        int col2 = Integer.parseInt(columns[1].trim());
                        String col3 = columns[2].trim();
                        String col4 = columns[3].trim();
                        CSVObjects record = new CSVObjects(col1, col2, col3, col4);
                        rows.add(record);
                    } catch (NumberFormatException e) {
                        // Handle parsing error if columns 1 or 2 are not integers
                        System.err.println("Data in columns 1 or 2 is invalid! Needs to be an Integer.");
                    }
                } else {
                    // Handle incorrect number of columns in a row
                    System.err.println("Invalid number of columns in the CSV file.");
                }
            }
        } catch (IOException e) {
            System.err.println("File could not be read!");
        }

        return rows;
    }
}
