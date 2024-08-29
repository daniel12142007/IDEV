package com.example.idevbackend.api;

import com.example.idevbackend.models.enums.Language;
import com.example.idevbackend.payload.request.CourseRequest;
import com.example.idevbackend.payload.request.CourseUpdateRequest;
import com.example.idevbackend.payload.response.CourseResponse;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/course")
public class CourseApi {
    private final CourseService courseService;

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Создание нового курса", description = "Добавление нового курса в систему с указанием названия, описания и языка")
    public CourseResponse saveCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(courseRequest);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Обновление существующего курса", description = "Обновление информации о курсе по идентификатору")
    public CourseResponse updateCourse(@PathVariable Long id, @RequestBody CourseUpdateRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Удаление курса", description = "Удаление курса по идентификатору")
    public MessageResponse deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение курса по ID", description = "Получение информации о курсе по его идентификатору")
    public CourseResponse findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @GetMapping("find/all")
    @Operation(summary = "Получение всех курсов по языку", description = "Получение списка всех курсов, фильтрованных по языку")
    public List<CourseResponse> findAll(@RequestParam(required = false) Language language) {
        return courseService.findAll(language);
    }
}