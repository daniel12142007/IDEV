package com.example.idevbackend.repositories;

import com.example.idevbackend.models.Direction;
import com.example.idevbackend.models.enums.Language;
import com.example.idevbackend.payload.response.DirectionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    boolean existsByTitleAndLanguage(String title, Language language);

    boolean existsByTitleAndIdNotAndLanguage(String title, Long id,Language language);

    @Query("""
            select new com.example.idevbackend.payload.response.DirectionResponse(
            d.id,
            d.title,
            d.language
            ) from Direction d
            where d.language = :language
            order by d.id desc
            """)
    List<DirectionResponse> findAllResponseByLanguage(@Param("language") Language language);
}