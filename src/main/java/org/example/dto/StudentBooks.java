package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
public class StudentBooks {
    private Integer id;
    private Integer student_id;
    private Integer book_id;
    private LocalDate createdDate;
    private String status; //(TAKEN,RETURNED)
    private LocalDate  returnedDate;
    private LocalDate duration;
}
