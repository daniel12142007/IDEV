package com.example.idevbackend.services;

import com.example.idevbackend.exceptions.NotFoundException;
import com.example.idevbackend.models.Project;
import com.example.idevbackend.models.enums.Language;
import com.example.idevbackend.payload.request.ProjectRequest;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.payload.response.ProjectResponse;
import com.example.idevbackend.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectResponse save(ProjectRequest projectRequest, Language language) {
        log.info("Сохранение проекта с заголовком '{}' начато", projectRequest.title());
        if (projectRequest.title().isBlank()) {
            log.error("Ошибка сохранения проекта: заголовок не может быть пустым");
            throw new IllegalArgumentException("Title cannot be blank");
        }
        Project project = Project.builder()
                .title(projectRequest.title())
                .image(projectRequest.image())
                .link(projectRequest.link())
                .description(projectRequest.description())
                .language(language)
                .build();
        projectRepository.save(project);
        log.info("Проект с заголовком '{}' успешно сохранен", projectRequest.title());
        return findById(project.getId());
    }

    public ProjectResponse update(Long id, ProjectRequest projectRequest) {
        log.info("Обновление проекта с ID {} начато", id);
        if (projectRequest.title().isBlank()) {
            log.error("Ошибка обновления проекта: заголовок не может быть пустым");
            throw new IllegalArgumentException("Title cannot be blank");
        }
        Project project = projectRepository.findById(id).orElseThrow(() -> {
            log.error("Проект с ID {} не найден", id);
            return new NotFoundException("Project not found");
        });
        project.setTitle(projectRequest.title());
        project.setImage(projectRequest.image());
        project.setLink(projectRequest.link());
        project.setDescription(projectRequest.description());
        projectRepository.save(project);
        log.info("Проект с ID {} успешно обновлен", id);
        return findById(project.getId());
    }

    public MessageResponse delete(Long id) {
        log.info("Удаление проекта с ID {} начато", id);
        Project project = projectRepository.findById(id).orElseThrow(() -> {
            log.error("Проект с ID {} не найден", id);
            return new NotFoundException("Project not found");
        });
        projectRepository.delete(project);
        log.info("Проект с ID {} успешно удален", id);
        return new MessageResponse("Project deleted successfully");
    }

    public ProjectResponse findById(Long id) {
        log.info("Поиск проекта с ID {} начат", id);
        Project project = projectRepository.findById(id).orElseThrow(() -> {
            log.error("Проект с ID {} не найден", id);
            return new NotFoundException("Project not found");
        });
        log.info("Проект с ID {} найден", id);
        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getImage(),
                project.getLink(),
                project.getDescription(),
                project.getLanguage()
        );
    }

    public List<ProjectResponse> findAllByLanguage(Language language) {
        log.info("Поиск всех проектов для языка {} начат", language);
        log.info("Проекты для языка {} успешно найдены", language);
        return projectRepository.findAllResponseByLanguage(language);
    }
}