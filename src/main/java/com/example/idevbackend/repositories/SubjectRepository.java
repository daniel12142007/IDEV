package com.example.idevbackend.repositories;

import com.example.idevbackend.models.Subject;
import com.example.idevbackend.payload.response.SubjectResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("""
            select new com.example.idevbackend.payload.response.SubjectResponse(
            s.id,
            s.image,
            s.title,
            s.language,
            s.course.id
            ) from Subject s
            where s.course.id = :courseId
            order by s.id desc
            """)
    List<SubjectResponse> findAllResponseByCourseId(@Param("courseId") Long courseId);
}