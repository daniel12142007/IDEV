package com.example.idevbackend.api;

import com.example.idevbackend.payload.response.DiplomaResponse;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.services.DiplomaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/diploma")
public class DiplomaApi {
    private final DiplomaService diplomaService;

    @PostMapping("save/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Создание диплома",
            description = "Создает новый диплом для указанного курса")
    public DiplomaResponse saveDiploma(@PathVariable Long courseId, @RequestParam String title) {
        return diplomaService.save(courseId, title);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Обновление диплома",
            description = "Обновляет диплом по его ID")
    public DiplomaResponse updateDiploma(@PathVariable Long id, @RequestParam String title) {
        return diplomaService.update(id, title);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Удаление диплома",
            description = "Удаляет диплом по его ID")
    public MessageResponse deleteDiploma(@PathVariable Long id) {
        return diplomaService.delete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск диплома по ID",
            description = "Возвращает диплом по его ID")
    public DiplomaResponse findDiplomaById(@PathVariable Long id) {
        return diplomaService.findById(id);
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Поиск всех дипломов по ID курса",
            description = "Возвращает все дипломы для указанного курса")
    public List<DiplomaResponse> findAllDiplomasByCourseId(@PathVariable Long courseId) {
        return diplomaService.findAllByCourseId(courseId);
    }
}