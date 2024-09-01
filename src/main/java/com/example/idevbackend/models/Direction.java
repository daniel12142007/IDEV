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
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Language language;
    @OneToMany(mappedBy = "directions")
    private Set<Employee> employees;
}