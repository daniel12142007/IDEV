package com.example.idevbackend.services;

import com.example.idevbackend.exceptions.NotFoundException;
import com.example.idevbackend.models.Course;
import com.example.idevbackend.models.enums.Language;
import com.example.idevbackend.payload.request.CourseRequest;
import com.example.idevbackend.payload.response.CourseResponse;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseResponse saveCourse(CourseRequest courseRequest) {
        log.info("Сохранение курса с названием: {}", courseRequest.title());

        if (courseRepository.existsByTitle(courseRequest.title())) {
            log.error("Курс с названием '{}' уже существует", courseRequest.title());
            throw new IllegalArgumentException("Course title already exists");
        }

        Course course = Course.builder()
                .title(courseRequest.title())
                .description(courseRequest.description())
                .language(courseRequest.language())
                .build();

        courseRepository.save(course);
        log.info("Курс успешно сохранен: {}", course.getId());

        return findById(course.getId());
    }

    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        log.info("Обновление курса с ID: {}", id);

        if (courseRepository.existsByTitleAndIdNot(courseRequest.title(), id)) {
            log.error("Курс с названием '{}' уже существует для другого ID", courseRequest.title());
            throw new IllegalArgumentException("Course title already exists");
        }

        Course course = courseRepository.findById(id).orElseThrow(() -> {
            log.error("Курс с ID: {} не найден", id);
            return new NotFoundException("Not found course ID: " + id);
        });

        course = Course.builder()
                .id(course.getId())
                .title(courseRequest.title())
                .description(courseRequest.description())
                .language(courseRequest.language())
                .build();

        courseRepository.save(course);
        log.info("Курс с ID: {} успешно обновлен", id);

        return findById(id);
    }

    public MessageResponse deleteCourse(Long id) {
        log.info("Удаление курса с ID: {}", id);

        Course course = courseRepository.findById(id).orElseThrow(() -> {
            log.error("Курс с ID: {} не найден", id);
            return new NotFoundException("Not found course ID: " + id);
        });

        courseRepository.delete(course);
        log.info("Курс с ID: {} успешно удален", id);

        return new MessageResponse("Course deleted successfully");
    }

    public CourseResponse findById(Long id) {
        log.info("Поиск курса с ID: {}", id);

        Course course = courseRepository.findById(id).orElseThrow(() -> {
            log.error("Курс с ID: {} не найден", id);
            return new NotFoundException("Not found course ID: " + id);
        });

        log.info("Курс с ID: {} найден", id);
        return new CourseResponse(course.getId(), course.getTitle(), course.getDescription(), course.getLanguage());
    }

    public List<CourseResponse> findAll(Language language) {
        log.info("Поиск всех курсов с языком: {}", language);

        List<CourseResponse> courses = courseRepository.findAllResponseCourseByLanguage(language);
        log.info("Найдено {} курсов с языком: {}", courses.size(), language);

        return courses;
    }
}