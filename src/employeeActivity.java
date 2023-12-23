import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

public class CSVObject {
        private final int empID;
        private final int projectID;
        private final LocalDate startDate;
        private final LocalDate endDate;

        public CSVObject(int empID, int projectID, LocalDate startDate, LocalDate endDate) {
            this.empID = empID;
            this.projectID = projectID;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public static LocalDate parseDate(String dateStr) {
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
            return String.format("%s, %s, %s, %s", empID, projectID, startDate, endDate);
        }

    public int getEmpID() {
        return empID;
    }

    public int getProjectID() {
        return projectID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean hasCommonProject(CSVObject other) {
            return this.empID != other.empID &&
                    this.projectID == other.projectID &&
                    ((this.startDate.isBefore(other.startDate) && this.endDate.isAfter(other.endDate)) ||
                            (this.startDate.isAfter(other.startDate) && this.startDate.isBefore(other.endDate)));
    }

    public long calculateCommonDays(CSVObject other) {
            LocalDate commonFrom = this.startDate.isBefore(other.startDate) ? other.startDate : this.startDate;
            LocalDate commonTo = (this.endDate == null || other.endDate == null) ?
                    (this.endDate == null ? other.endDate : this.startDate) :
                    (this.endDate.isBefore(other.endDate) ? this.endDate : other.endDate);

            return Math.abs(ChronoUnit.DAYS.between(commonFrom, commonTo));
    }
}
