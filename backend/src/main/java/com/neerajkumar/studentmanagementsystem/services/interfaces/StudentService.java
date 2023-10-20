package com.neerajkumar.studentmanagementsystem.services.interfaces;

import com.neerajkumar.studentmanagementsystem.payloads.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto addStudent(StudentDto student);
    List<StudentDto> getAllStudents();
    void updateStudent(StudentDto student,Long id);
    StudentDto getStudentById(Long id);
    void deleteStudent(Long id);
}
