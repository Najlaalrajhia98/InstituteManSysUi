package com.thedynamicdoers.InstitueManagmentSys.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column
    public String name;
    @Column
    @Pattern(regexp="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message="Invalid email")
    public String email;
    @Column
    public Long hiredAt;
}
