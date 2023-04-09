package com.thedynamicdoers.InstitueManagmentSys.Controller;

import com.thedynamicdoers.InstitueManagmentSys.Model.Student;
import com.thedynamicdoers.InstitueManagmentSys.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The StudentController class is responsible for handling HTTP requests and responses for Student resource.
 * This class provides various HTTP methods such as GET, POST, PUT and DELETE to interact with Student resource.
 * It uses StudentService to perform CRUD operations on Student resource.
 */
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(path = "/api/Students")
public class StudentController {
    /**
     * The @Autowired annotation is used to inject the StudentService dependency into the StudentController class.
     * This allows us to use the methods provided by the StudentService class to interact with the Student resource.
     */
    @Autowired
    private StudentService studentService;

    /**
     * Returns a list of all students.
     *
     * @return List of students.
     */
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getListOfStudents();
    }

    /**
     * Returns a specific student.
     *
     * @param id The id of the student to retrieve.
     * @return The student with the given id.
     */
    @GetMapping(path = "/{id}")
    public Student getStudent(@PathVariable int id) {
        Student foundedStudent = studentService.getStudent(id);
        return foundedStudent;
    }

    /**
     * Creates a new student.
     *
     * @param newStudent The student object to create.
     * @return The created student object.
     */
    @PostMapping
    public Student createStudent(@Valid @RequestBody Student newStudent) {
        studentService.createStudent(newStudent);
        return newStudent;
    }

    /**
     * Updates an existing student.
     *
     * @param id             The id of the student to update.
     * @param currentStudent The updated student object.
     * @return The updated student object.
     */
    @PutMapping(path = "/{id}")
    public Student updateStudent(@PathVariable(name = "id") int id, @RequestBody Student currentStudent) {
        studentService.updateStudent(id, currentStudent);
        return currentStudent;
    }

    /**
     * Deletes a student.
     *
     * @param id The id of the student to delete.
     * @return The deleted student object.
     */
    @DeleteMapping(path = "/{id}")
    public Student removeStudent(@PathVariable int id) {
        Student removedStudent = studentService.deleteStudent(id);
        return removedStudent;
    }
}
