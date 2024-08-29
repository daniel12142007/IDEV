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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    private String link;
    @Column(length = 900)
    private String description;
    @Enumerated(EnumType.STRING)
    private Language language;
}