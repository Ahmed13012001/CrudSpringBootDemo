package com.example.CrudSpringBootDemo.controller;

import com.example.CrudSpringBootDemo.entity.Student;
import com.example.CrudSpringBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //create student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student)
    {
//        System.out.println("Inside createStudent---> mapping is correct");
//        System.out.println(student.getName()+"  "+student.getEmail());
        Student createdstudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdstudent);
    }
//    Read one student
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id)
    {
        Student student= studentService.getStudent(id);
//        System.out.println(student.getName());
        if(student==null)
            return ResponseEntity.notFound().build();

        return  ResponseEntity.ok(student);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Student>> getAll()
    {
        List<Student> students = studentService.get();
//        System.out.println(student.getName());
        if(students.isEmpty())
            return ResponseEntity.noContent().build();

        return  ResponseEntity.ok(students);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> studentUpdate(@PathVariable long id,@RequestBody Student student)
    {
       Student studentfound = studentService.update(id,student);
       if (studentfound == null)
           return ResponseEntity.notFound().build();
       return ResponseEntity.ok().body(studentfound);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> studentDelete(@PathVariable("Id") Long id)
    {
        boolean isDeleted = studentService.studentDelete(id);

        if(!isDeleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body("Record Deleted");
    }


}
