package com.thedynamicdoers.InstitueManagmentSys.Service;

import com.thedynamicdoers.InstitueManagmentSys.Model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class StudentService {
    public List<Student> listOfStudents =  new CopyOnWriteArrayList<>();
    int currId=1;

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }
    public Student getStudent(int id ){
        Student foundStudent = listOfStudents.stream().filter(
                (currentStudent) -> {
                    return currentStudent.id == id ;
                }
        ).findFirst().get();
        return foundStudent;
    }
    public Student deleteStudent(int id ){
        Student foundedStudent= getStudent(id);
        listOfStudents.remove(foundedStudent);
        return foundedStudent;
    }
    public Student createStudent(Student newStudent){
        newStudent.id= this.currId++;
        listOfStudents.add(newStudent);
        return newStudent;

    }
    public Student updateStudent(int id,Student upToDateStudent){
        Student foundedStudent = getStudent(id);
        foundedStudent.name= upToDateStudent.name;
        foundedStudent.email= upToDateStudent.email;
        return foundedStudent;

    }
}
