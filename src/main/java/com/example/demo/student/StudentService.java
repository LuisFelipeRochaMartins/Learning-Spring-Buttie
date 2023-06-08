package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
     public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void verifyStudentEmail(String email){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
    }

    public void addNewStudent(Student student){
        verifyStudentEmail(student.getEmail());
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with id "  + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new IllegalArgumentException(
                        "Student with id " + studentId + " does not exists"
                ));
        if(name != null && name.length() > 3 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            verifyStudentEmail(email);

            student.setEmail(email);
        }
    }
}
