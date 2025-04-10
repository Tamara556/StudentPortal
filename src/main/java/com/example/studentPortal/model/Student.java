package com.example.studentPortal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Lesson lesson;

}
