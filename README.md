# EmployeeProjectActivity
## Employee Activity Tracker
This Java project provides a simple CSV parser and activity tracker for managing employee activities. The application allows users to add, update, and remove employee activities, as well as display and analyze the data to identify common work periods between employees.

### Features
1. **CSV Parsing:** The project includes a CSVParser class that reads data from a CSV file, validates the format, and parses it into a list of employee activities.

2. **EmployeeActivity Class:** The EmployeeActivity class represents an employee's activity, including employee ID, project ID, start date, and end date. It also provides methods for checking common projects with another employee, calculating common days, and formatting data for CSV.

3. **Menu Class:** The Menu class serves as the main interface for users. It allows users to interact with the system through a console-based menu. Users can add, update, remove, and display employee activities, as well as calculate and display the longest common work period between employees.

4. **File Management:** The system can save employee activity data to a CSV file, ensuring that changes are persistent across program executions.

5. **Main Class:** The Main class initializes the application, checks for the existence of the CSV file, and creates it if it doesn't exist. It then initiates the Menu class to run the main interaction loop.

### Getting Started

1. **Prerequisites:**
..* Java Development Kit (JDK) installed on your machine.
2. **Setup:**
..*Clone or download the project to your local machine.
..*Ensure you have the necessary permissions to read and write to the CSV file.
3. **Run the Application:**
..*Navigate to the project directory.
..*Compile the Java files using a command like javac *.java.
..*Run the application with java Main.
4. **Interact with the Menu:**
..*Follow the on-screen menu options to perform various actions, such as adding, updating, or displaying employee activities.
..*To save changes and exit, select the "Save & Exit" option.

### Notes
..* This project is a console-based application and does not have a graphical user interface (GUI).

..* Ensure that the CSV file path is correctly set in the Menu class (String filePath = "textFile.csv";) to match the location of your CSV file.
