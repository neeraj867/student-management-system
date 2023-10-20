package com.neerajkumar.studentmanagementsystem.repositories;

import com.neerajkumar.studentmanagementsystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student,Long> {
    Optional<Student> findByEmail(String email);
}
