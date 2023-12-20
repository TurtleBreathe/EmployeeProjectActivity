import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class CSVObjects {
        private final int column1;
        private final int column2;
        private final LocalDate column3;
        private final LocalDate column4;

        public CSVObjects(int column1, int column2, String column3, String column4) {
            this.column1 = column1;
            this.column2 = column2;
            this.column3 = parseDate(column3);
            this.column4 = parseDate(column4);
        }

        private static LocalDate parseDate(String dateStr) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
                    .append(DateTimeFormatter.ofPattern("[dd-MM-yyyy]" + "[dd/MM/yyyy]" + "[dd.MM.yyyy]"
                            + "[MM-dd-yyyy]" + "[dd/MM/yyyy]" + "[dd.MM.yyyy]" + "[yyyy-MM-dd]" + "[yyyy/MM/dd]"
                            + "[yyyy.MM.dd]"));
            DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();

            try {
                return LocalDate.parse(dateStr, dateTimeFormatter);
            } catch (Exception e) {
                // Handle parsing error or try other date formats
                System.err.println("Unable to parse date: " + dateStr);
                System.err.println("Invalid date format");
                return  null;
            }
        }

        @Override
        public String toString() {
            return String.format("%s, %s, %s, %s", column1, column2, column3, column4);
        }
}
