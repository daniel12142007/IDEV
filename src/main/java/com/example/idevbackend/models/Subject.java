package com.example.idevbackend.models;

import com.example.idevbackend.models.enums.Language;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String title;
    @Enumerated(EnumType.STRING)
    private Language language;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}