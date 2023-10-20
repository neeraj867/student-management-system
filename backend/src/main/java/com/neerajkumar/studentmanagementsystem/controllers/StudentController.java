package com.neerajkumar.studentmanagementsystem.controllers;

import com.neerajkumar.studentmanagementsystem.payloads.Response;
import com.neerajkumar.studentmanagementsystem.payloads.StudentDto;
import com.neerajkumar.studentmanagementsystem.services.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/test")
    public String apiTest() {
        return "Student apis works!!";
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> list = this.studentService.getAllStudents();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto){
        StudentDto response = this.studentService.addStudent(studentDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDto studentDto, @PathVariable Long id) {
        this.studentService.updateStudent(studentDto,id);
        return new ResponseEntity<>(new Response("student is successfully updated",true),HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto response = this.studentService.getStudentById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        this.studentService.deleteStudent(id);
        return new ResponseEntity<>(new Response("student is successfully deleted",true),HttpStatus.OK);
    }
}
