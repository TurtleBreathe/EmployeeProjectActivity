import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public static List<employeeActivity> parseCSV(String filePath) {
        List<employeeActivity> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");
                if (columns.length == 4) {
                    try {
                        int empID = Integer.parseInt(columns[0].trim());
                        int projectID = Integer.parseInt(columns[1].trim());
                        LocalDate startDate = employeeActivity.parseDate(columns[2].trim());
                        LocalDate endDate = columns[3].equalsIgnoreCase("NULL") ? LocalDate.now() : employeeActivity.parseDate(columns[3].trim());
                        employeeActivity record = new employeeActivity(empID, projectID, startDate, endDate);
                        rows.add(record);
                    } catch (NumberFormatException e) {
                        System.err.println("Data in columns 1 or 2 is invalid! Must be an Integer.");
                    }
                } else {
                    System.err.println("Invalid number of columns in the CSV file! Must have 4 columns.");
                }
            }
        } catch (IOException e) {
            System.err.println("File could not be read!");
        }

        return rows;
    }
}
