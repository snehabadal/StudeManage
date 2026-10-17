package com.example.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public Student addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student is required");
        }
        if (findStudent(student.getId()).isPresent()) {
            throw new IllegalArgumentException(
                    "Student ID already exists: " + student.getId());
        }
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return List.copyOf(students);
    }

    public Optional<Student> findStudent(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }

    public Student updateStudent(int id, String name, String course, String email) {
        int index = indexOf(id);
        if (index == -1) {
            throw new IllegalArgumentException("Student not found: " + id);
        }
        Student updated = new Student(id, name, course, email);
        students.set(index, updated);
        return updated;
    }

    public boolean deleteStudent(int id) {
        return students.removeIf(student -> student.getId() == id);
    }

    private int indexOf(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
