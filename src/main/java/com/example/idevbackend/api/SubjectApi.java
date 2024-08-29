package com.example.idevbackend.api;

import com.example.idevbackend.payload.request.SubjectRequest;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.payload.response.SubjectResponse;
import com.example.idevbackend.services.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/subject")
public class SubjectApi {
    private final SubjectService subjectService;

    @PostMapping("save/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Сохранение Subject", description = "Сохраняет новый Subject для заданного курса")
    public SubjectResponse saveSubject(@PathVariable Long courseId, @RequestBody SubjectRequest request) {
        return subjectService.save(courseId, request);
    }

    @PutMapping("update/{subjectId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Обновление Subject", description = "Обновляет существующий Subject по его ID")
    public SubjectResponse updateSubject(@PathVariable Long subjectId, @RequestBody SubjectRequest request) {
        return subjectService.update(subjectId, request);
    }

    @DeleteMapping("delete/{subjectId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Удаление Subject", description = "Удаляет Subject по его ID")
    public MessageResponse deleteSubject(@PathVariable Long subjectId) {
        return subjectService.delete(subjectId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск Subject по ID", description = "Возвращает Subject по его ID")
    public SubjectResponse findSubjectById(@PathVariable Long id) {
        return subjectService.findById(id);
    }

    @GetMapping("find/all/subjects/{courseId}")
    @Operation(summary = "Поиск всех Subjects по COURSE_ID курса", description = "Возвращает все Subjects для заданного курса")
    public List<SubjectResponse> findAllSubjectsByCourse(@PathVariable Long courseId) {
        return subjectService.findAllByCourse(courseId);
    }
}
