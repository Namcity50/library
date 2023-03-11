package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class Book {
    private Integer id;
    private String title;
    private String author;
    private LocalDate publishYear;
    private Double amount;
    private boolean visible;
}
