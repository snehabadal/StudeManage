package com.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService();
    }

    @Test
    void shouldAddStudent() {
        Student student = service.addStudent(
                new Student(1, "Amit", "BCA", "amit@example.com"));
        assertEquals(1, student.getId());
        assertEquals(1, service.getAllStudents().size());
    }

    @Test
    void shouldRejectDuplicateStudentId() {
        service.addStudent(new Student(1, "Amit", "BCA", "amit@example.com"));
        assertThrows(IllegalArgumentException.class,
                () -> service.addStudent(
                        new Student(1, "Neha", "MCA", "neha@example.com")));
    }

    @Test
    void shouldFindStudentById() {
        service.addStudent(new Student(2, "Neha", "MCA", "neha@example.com"));
        assertTrue(service.findStudent(2).isPresent());
        assertEquals("Neha", service.findStudent(2).orElseThrow().getName());
    }

    @Test
    void shouldUpdateStudent() {
        service.addStudent(new Student(3, "Raj", "BCA", "raj@example.com"));
        Student updated = service.updateStudent(
                3, "Raj Patel", "MCA", "rajp@example.com");
        assertEquals("Raj Patel", updated.getName());
        assertEquals("MCA", updated.getCourse());
    }

    @Test
    void shouldDeleteStudent() {
        service.addStudent(new Student(4, "Priya", "BCA", "priya@example.com"));
        assertTrue(service.deleteStudent(4));
        assertTrue(service.getAllStudents().isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenNoStudentsExist() {
        List<Student> students = service.getAllStudents();
        assertNotNull(students);
        assertTrue(students.isEmpty());
    }

    @Test
    void shouldRejectInvalidStudentId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Student(0, "Amit", "BCA", "amit@example.com"));
    }

    @Test
    void shouldRejectBlankStudentName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Student(5, " ", "BCA", "amit@example.com"));
    }
}
