package com.example.idevbackend.repositories;

import com.example.idevbackend.models.Course;
import com.example.idevbackend.models.Direction;
import com.example.idevbackend.models.Employee;
import com.example.idevbackend.payload.response.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("""
            select new com.example.idevbackend.payload.response.EmployeeResponse(
            e.id,
            e.fullName,
            e.image,
            e.linkLinkedIn,
            e.direction.title
            )from Employee e
            order by e.id desc
            """)
    List<EmployeeResponse> findAllResponse();

    @Query("""
            select new com.example.idevbackend.payload.response.EmployeeResponse(
            e.id,
            e.fullName,
            e.image,
            e.linkLinkedIn,
            e.direction.title
            )from Employee e
            where e.direction.id = :directionId
            order by e.id desc
            """)
    List<EmployeeResponse> findAllByDirectionId(@Param("directionId") Long directionId);

    @Query("""
            select new com.example.idevbackend.payload.response.EmployeeResponse(
            e.id,
            e.fullName,
            e.image,
            e.linkLinkedIn,
            e.direction.title
            )from Employee e
            join e.courses c
            on c.id = :courseId
            order by e.id desc
            """)
    List<EmployeeResponse> findAllByCourseId(@Param("courseId") Long courseId);

    List<Employee> findAllByDirection(Direction direction);

    @Query("select e from Employee e join e.courses c on c = :course")
    List<Employee> findAllByCourse(@Param("course") Course course);
}