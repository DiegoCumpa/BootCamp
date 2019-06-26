package com.example.diego.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class StudentClassKey implements Serializable {

    private static final long serialVersionUID = 1;

    @Column(name = "student_id")
    int studentId;
    @Column(name = "class_id")
    int classId;
    @Column(name = "date_from")
    Date dateFrom;

}
