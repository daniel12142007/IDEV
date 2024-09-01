package com.example.idevbackend.repositories;

import com.example.idevbackend.models.Employee;
import com.example.idevbackend.models.enums.Language;
import com.example.idevbackend.payload.response.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("""
            select new com.example.idevbackend.payload.response.EmployeeResponse(
            e.id,
            e.fullName,
            e.image,
            e.linkLinkedIn,
            e.language,
            e.directions.title
            ) from Employee e
            where e.language = :language
            order by e.id desc
            """)
    List<EmployeeResponse> findAllResponseByLanguage(@Param("language") Language language);
}