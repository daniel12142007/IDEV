package com.example.idevbackend.services;

import com.example.idevbackend.exceptions.NotFoundException;
import com.example.idevbackend.models.Course;
import com.example.idevbackend.models.Subject;
import com.example.idevbackend.payload.request.SubjectRequest;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.payload.response.SubjectResponse;
import com.example.idevbackend.repositories.CourseRepository;
import com.example.idevbackend.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;

    public SubjectResponse save(Long courseId, SubjectRequest request) {
        log.info("Сохранение Subject для Course с ID: {}", courseId);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Course с ID {} не найден", courseId);
            return new NotFoundException("Course not found");
        });

        Subject subject = Subject.builder()
                .image(request.image())
                .title(request.title())
                .language(course.getLanguage())
                .course(course)
                .build();

        subjectRepository.save(subject);
        log.info("Subject с ID: {} успешно сохранен", subject.getId());
        return findById(subject.getId());
    }

    public SubjectResponse update(Long subjectId, SubjectRequest request) {
        log.info("Обновление Subject с ID: {}", subjectId);
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> {
            log.error("Subject с ID {} не найден", subjectId);
            return new NotFoundException("Subject not found");
        });

        subject = Subject.builder()
                .image(request.image())
                .title(request.title())
                .language(subject.getLanguage())
                .course(subject.getCourse())
                .build();

        subjectRepository.save(subject);
        log.info("Subject с ID: {} успешно обновлен", subjectId);
        return findById(subjectId);
    }

    public MessageResponse delete(Long subjectId) {
        log.info("Удаление Subject с ID: {}", subjectId);
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> {
            log.error("Subject с ID {} не найден", subjectId);
            return new NotFoundException("Subject not found");
        });

        subjectRepository.delete(subject);
        log.info("Subject с ID: {} успешно удален", subjectId);
        return new MessageResponse("Subject deleted successfully");
    }

    public SubjectResponse findById(Long id) {
        log.info("Поиск Subject по ID: {}", id);
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> {
            log.error("Subject с ID {} не найден", id);
            return new NotFoundException("Subject not found");
        });

        log.info("Subject с ID: {} найден", id);
        return new SubjectResponse(
                subject.getId(),
                subject.getImage(),
                subject.getTitle(),
                subject.getLanguage(),
                subject.getCourse().getId()
        );
    }

    public List<SubjectResponse> findAllByCourse(Long courseId) {
        log.info("Поиск всех Subjects для Course с ID: {}", courseId);
        courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Course с ID {} не найден", courseId);
            return new NotFoundException("Course not found");
        });

        List<SubjectResponse> subjects = subjectRepository.findAllResponseByCourseId(courseId);
        log.info("Найдено {} Subjects для Course с ID: {}", subjects.size(), courseId);
        return subjects;
    }
}
