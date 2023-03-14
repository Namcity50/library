package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
public class Order {
    private Integer id;
    private String text;
    private LocalDate createdDate;
    private Integer studentId;

}
