package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add new student");
            System.out.println("2. Save to file");
            System.out.println("3. Display all students");
            System.out.println("4. Clear data file");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter EnrolID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter First Name: ");
                    String fn = sc.nextLine();
                    System.out.print("Enter Last Name: ");
                    String ln = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    service.addStudent(new Student(id, fn, ln, age));
                }
                case 2 -> service.saveToFile();
                case 3 -> service.displayFromFile();
                case 4 -> service.clearDataFile();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
