import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Menu {
    String filePath = "textFile.csv";
    List<employeeActivity> csvData = CSVParser.parseCSV(filePath);
    public void addActivity(employeeActivity activity) {
        csvData.add(activity);
    }

    public void update(employeeActivity activity) {
        for (int i = 0; i < csvData.size(); i++) {
            if (csvData.get(i).getEmpID() == activity.getEmpID()) {
                csvData.set(i, activity);
                return;
            }
        }
    }

    public void remove(int ID) {
        for (int i = 0; i < csvData.size(); i++) {
            if (csvData.get(i).getEmpID() == ID) {
                csvData.remove(i);
                return;
            }
        }
    }

    public void saveFile() {
        String outputFile = "textFile.csv";
        writeCSV(outputFile, csvData);
    }

    public static Map<Integer, List<employeeActivity>> groupProjectsByEmployee(List<employeeActivity> projects) {
        Map<Integer, List<employeeActivity>> employeeProjects = new HashMap<>();

        for (employeeActivity project : projects) {
            employeeProjects.computeIfAbsent(project.getEmpID(), k -> new ArrayList<>());
            employeeProjects.get(project.getEmpID()).add(project);
        }

        return employeeProjects;
    }

    public static Map<Integer, Map<Integer, Long>> calculateWorkPeriods(Map<Integer, List<employeeActivity>> employeeProjects) {
        Map<Integer, Map<Integer, Long>> workPeriods = new HashMap<>();

        for (List<employeeActivity> projects1 : employeeProjects.values()) {
            for (employeeActivity project1 : projects1) {
                for (List<employeeActivity> projects2 : employeeProjects.values()) {
                    for (employeeActivity project2 : projects2) {
                        if (project1.hasCommonProject(project2)) {

                            long commonDays = project1.calculateCommonDays(project2);

                            workPeriods.computeIfAbsent(project1.getEmpID(), k -> new HashMap<>());
                            workPeriods.get(project1.getEmpID()).put(project2.getEmpID(), commonDays);
                        }
                    }
                }
            }
        }

        return workPeriods;
    }

    public void display() {
        Map<Integer, List<employeeActivity>> employeeProjects = groupProjectsByEmployee(csvData);
        Map<Integer, Map<Integer, Long>> workPeriods = calculateWorkPeriods(employeeProjects);

        displayResults(workPeriods);
    }
    private static void displayResults(Map<Integer, Map<Integer, Long>> workTime) {
        Long maxDays = 0L;
        int emp1 = 0, emp2 = 0;

        for (Map.Entry<Integer, Map<Integer, Long>> entry : workTime.entrySet()) {
            for (Map.Entry<Integer, Long> projectEntry : entry.getValue().entrySet()) {
                Long commonDays = projectEntry.getValue();

                if (commonDays > maxDays) {
                    maxDays = commonDays;
                    emp1 = entry.getKey();
                    emp2 = projectEntry.getKey();
                }
            }
        }

        if (maxDays > 0) {
            System.out.println(emp1 + ", " + emp2 + ", " + maxDays);
        } else {
            System.out.println("No common projects found.");
        }
    }

    public static void writeCSV(String filePath, List<employeeActivity> records) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("EmpID;ProjectID;DateFrom;DateTo\n");

            for (employeeActivity record : records) {
                writer.write(record.toCSVString() + "\n");
            }
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("Could not write to CSV file: " + e.getMessage());
        }
    }

    public static void displayOptions() {
        System.out.println("1. Add Activity");
        System.out.println("2. Update Activity");
        System.out.println("3. Remove Activity EmployeeID");
        System.out.println("4. Display Activity");
        System.out.println("5. Show Longest Period");
        System.out.println("6. Save & Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        displayOptions();
        boolean active = true;
        while (active) {
            String command = scanner.nextLine();
            if (command.equals("Save & Exit")) {
                saveFile();
                active = false;
            } else {
                String[] tokens = command.split(" ");

                switch (tokens[0]) {
                    case "Add":
                    case "add":
                        System.out.println("Type the activity in the following format - { EmpID, ProjectID, DateFrom, DateTo }");
                        String[] info = scanner.nextLine().split(", ");
                        employeeActivity activity = new employeeActivity(
                                Integer.parseInt(info[0]),
                                Integer.parseInt(info[1]),
                                employeeActivity.parseDate(info[2].trim()),
                                info[3].equalsIgnoreCase("NULL") ? LocalDate.now() : employeeActivity.parseDate(info[3].trim()));
                        addActivity(activity);
                        System.out.println("Added Activity");
                        break;

                    case "Update":
                    case "update":
                        String[] empToUpdate = scanner.nextLine().split(", ");
                        activity = new employeeActivity(
                                Integer.parseInt(empToUpdate[0]),
                                Integer.parseInt(empToUpdate[1]),
                                employeeActivity.parseDate(empToUpdate[2].trim()),
                                empToUpdate[3].equalsIgnoreCase("NULL") ? LocalDate.now() : employeeActivity.parseDate(empToUpdate[3].trim()));
                        update(activity);
                        System.out.println("Updated Client");
                        break;

                    case "Remove":
                    case "remove":
                        remove(Integer.parseInt(tokens[2]));
                        System.out.println("Removed Activity");
                        break;

                    case "Display":
                    case "display":
                        if (!csvData.isEmpty()) {
                            for (employeeActivity record : csvData) {
                                System.out.println(record);
                            }
                        } else {
                            System.out.println("File is empty. Nothing to display.");
                        }

                        break;

                    case "Show":
                    case "show":
                        display();
                        break;

                    default:
                        System.out.println("Choose an option from the menu.");
                        displayOptions();
                }
            }
        }
    }
}
