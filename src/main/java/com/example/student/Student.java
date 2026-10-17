package com.example.student;

public class Student {
    private final int id;
    private final String name;
    private final String course;
    private final String email;

    public Student(int id, String name, String course, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        this.id = id;
        this.name = requireText(name, "Name");
        this.course = requireText(course, "Course");
        this.email = requireText(email, "Email");
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " is required");
        }
        return value.trim();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Student{id=" + id
                + ", name='" + name + '\''
                + ", course='" + course + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
