package com.Graduation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Student extends BaseEntity{


    private String name;

    private String fNumber;


    public String getName() {
        return name;
    }

    public String getfNumber() {
        return fNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }
}
