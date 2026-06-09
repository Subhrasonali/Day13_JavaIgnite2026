/*
Create a Java program that connects to a MySQL database college_db.

The table students contains:

id
name
marks
Task:
Fetch all student records
Display only students who scored above average marks
Also print total number of students processed
Hint:

Think in steps:

Fetch all data using ResultSet
First calculate average marks
Then filter while reading result
*/
import java.sql.*;

public class StudentDatabaseReportSystem {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/college_db";
        String user = "root";
        String password = "Subhra@2006";

        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            Connection con = DriverManager.getConnection(url, user, password);

            // Calculate Average Marks
            String avgQuery = "SELECT AVG(marks) AS avg_marks FROM students";
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(avgQuery);

            double avgMarks = 0;

            if (rs1.next()) {
                avgMarks = rs1.getDouble("avg_marks");
            }

            System.out.println("Average Marks: " + avgMarks);

            // Fetch All Students
            String query = "SELECT * FROM students";
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(query);

            int count = 0;

            System.out.println("\nStudents Scoring Above Average:");

            while (rs2.next()) {
                count++;

                int id = rs2.getInt("id");
                String name = rs2.getString("name");
                double marks = rs2.getDouble("marks");

                if (marks > avgMarks) {
                    System.out.println(
                        "ID: " + id +
                        ", Name: " + name +
                        ", Marks: " + marks
                    );
                }
            }

            System.out.println("\nTotal Students Processed: " + count);

            rs1.close();
            rs2.close();
            st1.close();
            st2.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}