package com.neerajkumar.studentmanagementsystem.services.implementations;

import com.neerajkumar.studentmanagementsystem.exceptionHandling.ResourceAlreadyExistsException;
import com.neerajkumar.studentmanagementsystem.exceptionHandling.ResourceNotFoundException;
import com.neerajkumar.studentmanagementsystem.models.Student;
import com.neerajkumar.studentmanagementsystem.payloads.StudentDto;
import com.neerajkumar.studentmanagementsystem.repositories.StudentRepo;
import com.neerajkumar.studentmanagementsystem.services.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;
    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        if(studentAlreadyExists(studentDto.getEmail())) {
            throw new ResourceAlreadyExistsException("student","emailId",studentDto.getEmail());
        }
        Student student = this.modelMapper.map(studentDto, Student.class);
        Student savedStudent = this.studentRepo.save(student);
        return this.modelMapper.map(savedStudent, StudentDto.class);
    }

    private boolean studentAlreadyExists(String email) {
        return this.studentRepo.findByEmail(email).isPresent();
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> studentsList = this.studentRepo.findAll();
        return studentsList.stream().map((student)-> this.modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public void updateStudent(StudentDto studentDto, Long id) {
        Student student = this.studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setDepartment(studentDto.getDepartment());
        this.studentRepo.save(student);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = this.studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
        return this.modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        if(!this.studentRepo.existsById(id)) {
            throw new ResourceNotFoundException("Student","id",id);
        }
        this.studentRepo.deleteById(id);
    }
}
