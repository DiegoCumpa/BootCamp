package com.example.diego.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_classes")
public class StudentClass {

    @EmbeddedId
    private StudentClassKey id;


    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("class_id")
    @JoinColumn(name = "class_id")
    private Classes classes;

    private Date dateTo;

}
