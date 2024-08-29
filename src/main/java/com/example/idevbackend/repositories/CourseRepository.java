package com.example.idevbackend.repositories;

import com.example.idevbackend.models.Course;
import com.example.idevbackend.models.enums.Language;
import com.example.idevbackend.payload.response.CourseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTitle(String title);

    boolean existsByTitleAndIdNot(String title, Long id);

    @Query("""
            select new com.example.idevbackend.payload.response.CourseResponse(
            c.id,
            c.title,
            c.description,
            c.language
            )from Course c
            where c.language = :language
            order by c.id desc
            """)
    List<CourseResponse> findAllResponseCourseByLanguage(@Param("language") Language language);
}