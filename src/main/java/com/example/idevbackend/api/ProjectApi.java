package com.example.idevbackend.api;

import com.example.idevbackend.models.enums.Language;
import com.example.idevbackend.payload.request.ProjectRequest;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.payload.response.ProjectResponse;
import com.example.idevbackend.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/project")
public class ProjectApi {
    private final ProjectService projectService;

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Создание нового проекта",
            description = "Добавление нового проекта с указанием заголовка, изображения, ссылки, описания и языка")
    public ProjectResponse saveProject(@RequestBody ProjectRequest projectRequest, @RequestParam Language language) {
        return projectService.save(projectRequest, language);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Обновление существующего проекта",
            description = "Обновление информации о проекте по идентификатору")
    public ProjectResponse updateProject(@PathVariable Long id, @RequestBody ProjectRequest projectRequest) {
        return projectService.update(id, projectRequest);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Удаление проекта",
            description = "Удаление проекта по идентификатору")
    public MessageResponse deleteProject(@PathVariable Long id) {
        return projectService.delete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение проекта по ID",
            description = "Получение информации о проекте по его идентификатору")
    public ProjectResponse findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @GetMapping("find/all")
    @Operation(summary = "Получение всех проектов по языку",
            description = "Получение списка всех проектов, фильтрованных по языку")
    public List<ProjectResponse> findAll(@RequestParam(required = false) Language language) {
        return projectService.findAllByLanguage(language);
    }
}