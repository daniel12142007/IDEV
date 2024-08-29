package com.example.idevbackend.models;

import com.example.idevbackend.models.enums.Language;
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
    @Enumerated(EnumType.STRING)
    private Language language;
    @ManyToMany
    @JoinTable(name = "employee_direction",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id"))
    private Set<Direction> directions;
    @ManyToMany
    @JoinTable(name = "employee_course",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

}