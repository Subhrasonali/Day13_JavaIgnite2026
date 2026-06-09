/*
Create a program that stores employee data in memory:

Each employee has:

id
department
salary
Task:
Store employees using ArrayList
Create a HashMap where:
Key = Department
Value = Total salary of that department
Display department with highest total salary
Hint:

Break into:

Grouping logic
Summation logic
Comparison logic
*/
import java.util.ArrayList;
import java.util.HashMap;

class Employee {
    int id;
    String department;
    double salary;

    Employee(int id, String department, double salary) {
        this.id = id;
        this.department = department;
        this.salary = salary;
    }
}

public class EmpDataAnalyzer {
    public static void main(String[] args) {

        // Store employees using ArrayList
        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee(101, "IT", 50000));
        employees.add(new Employee(102, "HR", 30000));
        employees.add(new Employee(103, "IT", 60000));
        employees.add(new Employee(104, "Finance", 70000));
        employees.add(new Employee(105, "HR", 40000));

        // HashMap: Department -> Total Salary
        HashMap<String, Double> deptSalary = new HashMap<>();

        // Summation and Grouping Logic
        for (Employee emp : employees) {
            deptSalary.put(
                emp.department,
                deptSalary.getOrDefault(emp.department, 0.0) + emp.salary
            );
        }

        // Display total salary of each department
        System.out.println("Department-wise Total Salary:");
        for (String dept : deptSalary.keySet()) {
            System.out.println(dept + " = " + deptSalary.get(dept));
        }

        // Comparison Logic
        String highestDept = "";
        double maxSalary = 0;

        for (String dept : deptSalary.keySet()) {
            if (deptSalary.get(dept) > maxSalary) {
                maxSalary = deptSalary.get(dept);
                highestDept = dept;
            }
        }

        System.out.println("\nDepartment with Highest Total Salary:");
        System.out.println(highestDept + " = " + maxSalary);
    }
}