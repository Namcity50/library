package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Student {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String status;
    private String role;
    private LocalDate createdDate;
    private Boolean visible;
}
