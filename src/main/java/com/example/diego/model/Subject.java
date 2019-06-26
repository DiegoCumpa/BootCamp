package com.example.diego.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subjectId;

    @NotBlank(message = "No puede ser vacío")
    @Column(name = "subject_name")
    private String subjectName;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Classes> listClasses;

    @Column(name = "isactive")
    private Integer status;


    public Subject(int subjectId, String subjectName, int status) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.status = status;
    }


}
