package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    // Đường dẫn lưu file
    private final String FILE_NAME = "C:\\StudentData\\students.dat";

    public StudentService() {
        // Tạo thư mục nếu chưa có
        File dir = new File(FILE_NAME).getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Thêm sinh viên mới vào danh sách tạm
    public void addStudent(Student s) {
        students.add(s);
        System.out.println(" Student added successfully!");
    }

    // Ghi danh sách sinh viên vào file .dat
    public void saveToFile() {
        if (students.isEmpty()) {
            System.out.println(" No students to save!");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            System.out.println(" Students saved successfully!");
            System.out.println(" File location: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println(" Error saving file: " + e.getMessage());
        }
    }

    // Đọc danh sách từ file .dat và hiển thị
    @SuppressWarnings("unchecked")
    public void displayFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("⚠️ No data file found at: " + FILE_NAME);
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Student> list = (List<Student>) ois.readObject();

            if (list.isEmpty()) {
                System.out.println(" File is empty!");
                return;
            }

            System.out.println("\nEnrolID\t\tFull Name\t\tAge");
            System.out.println("------------------------------------------------------");
            for (Student s : list) {
                System.out.printf("%-10s\t%-20s\t%d\n",
                        s.getEnrolId(), s.getFullName(), s.getAge());
            }
            System.out.println("------------------------------------------------------");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Xóa toàn bộ dữ liệu (reset file)
    public void clearDataFile() {
        File file = new File(FILE_NAME);
        if (file.exists() && file.delete()) {
            System.out.println("File deleted successfully!");
        } else {
            System.out.println("File not found or cannot delete.");
        }
    }
}
