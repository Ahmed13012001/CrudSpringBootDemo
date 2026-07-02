package com.example.CrudSpringBootDemo.service;

import com.example.CrudSpringBootDemo.entity.Student;
import com.example.CrudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }
    public Student saveStudent(Student student)
    {
        return studentRepository.save(student);
    }

    public Student getStudent(long id) {
        Optional<Student> studnentOption =  studentRepository.findById(id);
        if(studnentOption.isPresent())
            return studnentOption.get();

        return null;

    }

    public List<Student> get() {
        return studentRepository.findAll();
    }

    public Student update(long id,Student updateStudent) {
        Optional<Student> foundStudent = studentRepository.findById(id);
        if(foundStudent.isEmpty())
        {
            return  null;
        }
        Student exisitngstudent = foundStudent.get();
        exisitngstudent.setName(updateStudent.getName());
        exisitngstudent.setEmail(updateStudent.getEmail());
        exisitngstudent.setAge(updateStudent.getAge());
        exisitngstudent.setRollNo(updateStudent.getRollNo());
        exisitngstudent.setSubject(updateStudent.getSubject());

        return studentRepository.save(exisitngstudent);

    }

    public boolean studentDelete(Long id)
    {
         boolean isPresent= studentRepository.existsById(id);
         if(!isPresent)
             return false;
        studentRepository.deleteById(id);
        return isPresent;
    }
}
