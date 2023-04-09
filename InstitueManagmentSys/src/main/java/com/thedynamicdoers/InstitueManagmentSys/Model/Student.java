package com.thedynamicdoers.InstitueManagmentSys.Model;


import jakarta.validation.constraints.Pattern;

public class Student {
    public int id;
    public String name;
    @Pattern(regexp="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message="Invalid email")
    public String email;

}