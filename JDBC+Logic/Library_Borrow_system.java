/*
Create a Java program connected to database library_db.

Table:

book_id
title
available_copies
Task:
Allow user to input a book ID
Check availability
If available:
Reduce copy count by 1
Print “Book Issued”
Else:
Print “Not Available”
Hint:

Think:

SELECT → check condition
UPDATE → modify data
Use if-else inside Java after ResultSet
*/
import java.sql.*;
import java.util.Scanner;

public class Library_Borrow_system {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "Subhra@2006";

        Scanner sc = new Scanner(System.in);

        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection con = DriverManager.getConnection(url, user, password);

            // Input Book ID
            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            // SELECT Query
            String selectQuery =
                    "SELECT available_copies FROM books WHERE book_id = ?";

            PreparedStatement ps1 = con.prepareStatement(selectQuery);
            ps1.setInt(1, bookId);

            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                int copies = rs.getInt("available_copies");

                if (copies > 0) {

                    // UPDATE Query
                    String updateQuery =
                            "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";

                    PreparedStatement ps2 =
                            con.prepareStatement(updateQuery);

                    ps2.setInt(1, bookId);

                    int rows = ps2.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Book Issued");
                    }

                    ps2.close();
                } else {
                    System.out.println("Not Available");
                }
            } else {
                System.out.println("Book ID not found");
            }

            rs.close();
            ps1.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}