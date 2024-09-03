package com.example.idevbackend.repositories;

import com.example.idevbackend.models.Direction;
import com.example.idevbackend.payload.response.DirectionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    boolean existsByTitle(String title);

    boolean existsByTitleAndIdNot(String title, Long id);

    @Query("""
            select new com.example.idevbackend.payload.response.DirectionResponse(
            d.id,
            d.title
            ) from Direction d
            order by d.id desc
            """)
    List<DirectionResponse> findAllResponseByLanguage();
}