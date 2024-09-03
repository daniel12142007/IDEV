package com.example.idevbackend.repositories;

import com.example.idevbackend.models.Project;
import com.example.idevbackend.models.enums.Language;
import com.example.idevbackend.payload.response.ProjectResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("""
            select new com.example.idevbackend.payload.response.ProjectResponse(
            p.id,
            p.title,
            p.image,
            p.link,
            p.description,
            p.language
            ) from Project p
            where p.language = :language
            order by p.id desc 
            """)
    List<ProjectResponse> findAllResponseByLanguage(@Param("language") Language language);
}