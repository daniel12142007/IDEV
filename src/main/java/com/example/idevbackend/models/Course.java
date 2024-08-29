package com.example.idevbackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 900)
    private String description;
    @OneToMany(mappedBy = "course")
    private Set<Subject> subjects;
    @ManyToMany(mappedBy = "courses")
    private Set<Employee> employees;
}