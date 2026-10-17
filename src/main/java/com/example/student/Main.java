package com.example.student;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();

        service.addStudent(new Student(
                101, "Chetan", "Java Development", "chetan@example.com"));
        service.addStudent(new Student(
                102, "Rahul", "Software Engineering", "rahul@example.com"));

        System.out.println("=== Student Management System ===");
        System.out.println("Total students: " + service.getAllStudents().size());
        service.getAllStudents().forEach(System.out::println);

        System.out.println("\nSearching for student ID 101:");
        service.findStudent(101).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Student not found"));
    }
}
