package com.example.idevbackend.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String image;
    private String linkLinkedIn;
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
    @ManyToMany
    @JoinTable(name = "employee_course",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

}