package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
public class GetStudentBook {
    private Integer id;
    private String name;
    private String title;
    private String author;
    private String status;
    private LocalDate taken_date;
    private LocalDate return_date;
    private Integer order_num;
}
