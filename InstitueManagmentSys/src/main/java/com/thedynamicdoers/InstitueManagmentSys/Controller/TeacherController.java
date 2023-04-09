package com.thedynamicdoers.InstitueManagmentSys.Controller;

import com.thedynamicdoers.InstitueManagmentSys.Model.Teacher;
import com.thedynamicdoers.InstitueManagmentSys.Service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(path = "/{id}")
    public Optional<Teacher> getTeacher(@PathVariable int id) {
        return teacherService.getSpecificTeacherInfo(id);
    }

    @PostMapping
    public Teacher hireTeacher( @Valid @RequestBody Teacher teacher) {
        return teacherService.hireTeacher(teacher);
    }

    @PutMapping(path = "/{id}")
    public Optional <Teacher> ubdateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        return teacherService.updateSpecificTeacherInfo(id, teacher);
    }

    @DeleteMapping(path = "/{id}")
    public Optional<Teacher> fireTeacher(@PathVariable int id) {
        return teacherService.fireTeacher(id);
    }

}
