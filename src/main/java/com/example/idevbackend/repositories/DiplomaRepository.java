package com.example.idevbackend.repositories;

import com.example.idevbackend.models.Diploma;
import com.example.idevbackend.payload.response.DiplomaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiplomaRepository extends JpaRepository<Diploma, Long> {
    @Query("""
            select new com.example.idevbackend.payload.response.DiplomaResponse(
            d.id,
            d.title,
            d.language,
            d.course.id
            ) from Diploma d
            where d.course.id = :courseId
            order by d.id
            """)
    List<DiplomaResponse> findAllResponseByCourseId(@Param("courseId") Long courseId);
}