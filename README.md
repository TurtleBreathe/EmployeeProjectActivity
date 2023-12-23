# EmployeeProjectActivity
## Employee Activity Tracker
This Java project provides a simple CSV parser and activity tracker for managing employee activities. The application allows users to add, update, and remove employee activities, as well as display and analyze the data to identify common work periods between employees.

### Features
CSV Parsing: The project includes a CSVParser class that reads data from a CSV file, validates the format, and parses it into a list of employee activities.

EmployeeActivity Class: The EmployeeActivity class represents an employee's activity, including employee ID, project ID, start date, and end date. It also provides methods for checking common projects with another employee, calculating common days, and formatting data for CSV.

Menu Class: The Menu class serves as the main interface for users. It allows users to interact with the system through a console-based menu. Users can add, update, remove, and display employee activities, as well as calculate and display the longest common work period between employees.

File Management: The system can save employee activity data to a CSV file, ensuring that changes are persistent across program executions.

Main Class: The Main class initializes the application, checks for the existence of the CSV file, and creates it if it doesn't exist. It then initiates the Menu class to run the main interaction loop.
