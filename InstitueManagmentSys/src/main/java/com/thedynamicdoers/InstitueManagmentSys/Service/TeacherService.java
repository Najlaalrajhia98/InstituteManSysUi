package com.thedynamicdoers.InstitueManagmentSys.Service;

import com.thedynamicdoers.InstitueManagmentSys.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TeacherService {
    private final List<Teacher> teacherlist = new CopyOnWriteArrayList<>();

    public Optional<Teacher> getSpecificTeacherInfo(int id) {
        return teacherlist.stream().filter(
                (currentTeacher) -> {
                    return currentTeacher.id == id;
                }
        ).findFirst();
    }

    public List<Teacher> getAllTeachers() {
        return teacherlist;
    }

    public Teacher hireTeacher(Teacher teacher) {
        teacherlist.add(teacher);
        return teacher;
    }

    public Optional<Teacher> fireTeacher(int id) {
        Optional<Teacher> teacher = getSpecificTeacherInfo(id);
        teacher.ifPresent(
                (currentTeacher) -> {
                    teacherlist.remove(teacher);
                });
        return teacher;
    }

    public Optional<Teacher> updateSpecificTeacherInfo(int id, Teacher upToDateTeacher) {
        Optional<Teacher> foundTeacher = getSpecificTeacherInfo(id);
        foundTeacher.ifPresent(
                (currTeacher) -> {
                    currTeacher.name = upToDateTeacher.name;
                    currTeacher.email = upToDateTeacher.email;
                    currTeacher.hiredAt = upToDateTeacher.hiredAt;

                }
        );
        return foundTeacher;

    }
}
