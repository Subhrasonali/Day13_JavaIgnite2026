/*
Create a Java program to manage student course performance.

Task:

You are given multiple students with the following details:

Student Name
Course Name
Marks
Requirements:
Store all student records using ArrayList
Use a HashMap<String, List<Integer>> where:
Key = Course Name
Value = List of marks in that course
Calculate and display:
Average marks for each course
Course with highest average
Hint:

Break the problem into steps:

First store raw data in ArrayList
Then group marks by course using HashMap

for each loop - enhanced for loop 
for(int i=0;i<list.size();i++)
Student s=list.get(i)

for(Student s : list)
String course = s.course 
int marks = s.marks 
if(map.containsKey(course))
ArrayList<Integer> marksList = map.get(course)
marksList.add(marks)

ArrayList<Integer> marksList = map.get(course)
marksList.add(marks)
map.put(course,marksList)


Then iterate HashMap to calculate averages - for(String course:map.keySet())- creating arraylist -int sum =0, 
for(int i=0;i<marksList.size();i++)
sum=sum+marksList(i)
double =(double)sum/marksList.size();


Finally compare averages
  */
import java.util.ArrayList;
import java.util.HashMap;

class Student {
    String name;
    String course;
    int marks;

    Student(String name, String course, int marks) {
        this.name = name;
        this.course = course;
        this.marks = marks;
    }
}

public class Performance_Analyzer {
    public static void main(String[] args) {

        // Store student records using ArrayList
        ArrayList<Student> list = new ArrayList<>();

        list.add(new Student("Rahul", "Java", 85));
        list.add(new Student("Priya", "Java", 90));
        list.add(new Student("Amit", "Python", 80));
        list.add(new Student("Neha", "Python", 70));
        list.add(new Student("Riya", "DBMS", 95));

        // HashMap<Course, List of Marks>
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();

        // Group marks by course
        for (Student s : list) {
            String course = s.course;
            int marks = s.marks;

            if (map.containsKey(course)) {
                ArrayList<Integer> marksList = map.get(course);
                marksList.add(marks);
            } else {
                ArrayList<Integer> marksList = new ArrayList<>();
                marksList.add(marks);
                map.put(course, marksList);
            }
        }

        // Calculate averages and find highest average
        String topCourse = "";
        double highestAvg = 0;

        System.out.println("Average Marks for Each Course:");

        for (String course : map.keySet()) {
            ArrayList<Integer> marksList = map.get(course);

            int sum = 0;
            for (int i = 0; i < marksList.size(); i++) {
                sum += marksList.get(i);
            }

            double avg = (double) sum / marksList.size();

            System.out.println(course + " = " + avg);

            if (avg > highestAvg) {
                highestAvg = avg;
                topCourse = course;
            }
        }

        System.out.println("\nCourse with Highest Average:");
        System.out.println(topCourse + " = " + highestAvg);
    }
}