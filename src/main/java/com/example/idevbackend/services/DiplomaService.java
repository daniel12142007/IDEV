package com.example.idevbackend.services;

import com.example.idevbackend.exceptions.NotFoundException;
import com.example.idevbackend.models.Course;
import com.example.idevbackend.models.Diploma;
import com.example.idevbackend.payload.response.DiplomaResponse;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.repositories.CourseRepository;
import com.example.idevbackend.repositories.DiplomaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiplomaService {
    private final DiplomaRepository diplomaRepository;
    private final CourseRepository courseRepository;

    public DiplomaResponse save(Long courseId, String title) {
        log.info("Сохранение диплома для курса с ID {} начато", courseId);
        if (title.isBlank()) {
            log.error("Ошибка сохранения диплома: заголовок не может быть пустым");
            throw new IllegalArgumentException("Title cannot be blank");
        }
        Course course = courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Курс с ID {} не найден", courseId);
            return new NotFoundException("Course not found");
        });
        Diploma diploma = Diploma.builder()
                .title(title)
                .course(course)
                .language(course.getLanguage())
                .build();
        diplomaRepository.save(diploma);
        log.info("Диплом с заголовком '{}' успешно сохранен", title);
        return findById(diploma.getId());
    }

    public DiplomaResponse update(Long id, String title) {
        log.info("Обновление диплома с ID {} начато", id);
        if (title.isBlank()) {
            log.error("Ошибка обновления диплома: заголовок не может быть пустым");
            throw new IllegalArgumentException("Title cannot be blank");
        }
        Diploma diploma = diplomaRepository.findById(id).orElseThrow(() -> {
            log.error("Диплом с ID {} не найден", id);
            return new NotFoundException("Diploma not found");
        });
        diploma.setTitle(title);
        diplomaRepository.save(diploma);
        log.info("Диплом с ID {} успешно обновлен", id);
        return findById(diploma.getId());
    }

    public MessageResponse delete(Long id) {
        log.info("Удаление диплома с ID {} начато", id);
        Diploma diploma = diplomaRepository.findById(id).orElseThrow(() -> {
            log.error("Диплом с ID {} не найден", id);
            return new NotFoundException("Diploma not found");
        });
        diplomaRepository.delete(diploma);
        log.info("Диплом с ID {} успешно удален", id);
        return new MessageResponse("Diploma deleted successfully");
    }

    public DiplomaResponse findById(Long id) {
        log.info("Поиск диплома с ID {} начат", id);
        Diploma diploma = diplomaRepository.findById(id).orElseThrow(() -> {
            log.error("Диплом с ID {} не найден", id);
            return new NotFoundException("Diploma not found");
        });
        log.info("Диплом с ID {} найден", id);
        return new DiplomaResponse(
                diploma.getId(),
                diploma.getTitle(),
                diploma.getLanguage(),
                diploma.getCourse().getId()
        );
    }

    public List<DiplomaResponse> findAllByCourseId(Long courseId) {
        log.info("Поиск всех дипломов для курса с ID {} начат", courseId);
        courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Курс с ID {} не найден", courseId);
            return new NotFoundException("Course not found");
        });
        log.info("Дипломы для курса с ID {} успешно найдены", courseId);
        return diplomaRepository.findAllResponseByCourseId(courseId);
    }
}