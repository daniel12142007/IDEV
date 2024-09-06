package com.example.idevbackend.services;

import com.example.idevbackend.exceptions.NotFoundException;
import com.example.idevbackend.models.Course;
import com.example.idevbackend.models.Direction;
import com.example.idevbackend.models.Employee;
import com.example.idevbackend.payload.request.EmployeeRequest;
import com.example.idevbackend.payload.response.EmployeeResponse;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.repositories.CourseRepository;
import com.example.idevbackend.repositories.DirectionRepository;
import com.example.idevbackend.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DirectionRepository directionRepository;
    private final CourseRepository courseRepository;
    private final S3FileService s3FileService;

    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest, Long directionId) {
        log.info("Сохранение сотрудника с именем: {}", employeeRequest.fullName());

        Direction direction = directionRepository.findById(directionId).orElseThrow(() -> {
            log.error("Направление с ID: {} не найдено", directionId);
            throw new IllegalArgumentException("Direction not found");
        });

        Employee employee = Employee.builder()
                .fullName(employeeRequest.fullName())
                .image(employeeRequest.image())
                .linkLinkedIn(employeeRequest.linkLinkedIn())
                .direction(direction)
                .build();

        employeeRepository.save(employee);
        log.info("Сотрудник успешно сохранен: {}", employee.getId());

        return findById(employee.getId());
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest, Long directionId) {
        log.info("Обновление сотрудника с ID: {}", id);

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error("Сотрудник с ID: {} не найден", id);
            return new NotFoundException("Not found employee ID: " + id);
        });

        Direction direction = directionRepository.findById(directionId).orElseThrow(() -> {
            log.error("Направление с ID: {} не найдено", directionId);
            throw new IllegalArgumentException("Direction not found");
        });

        employee = Employee.builder()
                .id(employee.getId())
                .fullName(employeeRequest.fullName())
                .image(employeeRequest.image())
                .linkLinkedIn(employeeRequest.linkLinkedIn())
                .direction(direction)
                .build();

        employeeRepository.save(employee);
        log.info("Сотрудник с ID: {} успешно обновлен", id);

        return findById(id);
    }

    public MessageResponse deleteEmployee(Long id) {
        log.info("Удаление сотрудника с ID: {}", id);

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error("Сотрудник с ID: {} не найден", id);
            return new NotFoundException("Not found employee ID: " + id);
        });

        employeeRepository.delete(employee);
        log.info("Сотрудник с ID: {} успешно удален", id);

        return new MessageResponse("Employee deleted successfully");
    }

    public EmployeeResponse findById(Long id) {
        log.info("Поиск сотрудника с ID: {}", id);

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error("Сотрудник с ID: {} не найден", id);
            return new NotFoundException("Not found employee ID: " + id);
        });

        log.info("Сотрудник с ID: {} найден", id);
        return new EmployeeResponse(
                employee.getId(),
                employee.getFullName(),
                employee.getImage(),
                employee.getLinkLinkedIn(),
                employee.getDirection().getTitle() // Assuming Direction has a getName() method
        );
    }

    public List<EmployeeResponse> findAllByDirection(Long directionId) {
        log.info("Поиск всех сотрудников по направлению с ID: {}", directionId);

        Direction direction = directionRepository.findById(directionId).orElseThrow(() -> {
            log.error("Направление с ID: {} не найдено", directionId);
            throw new IllegalArgumentException("Direction not found");
        });

        List<EmployeeResponse> employees = employeeRepository.findAllByDirectionId(direction.getId());
        log.info("Найдено {} сотрудников в направлении: {}", employees.size(), direction.getTitle());

        return employees;
    }

    public List<EmployeeResponse> findAllByCourse(Long courseId) {
        log.info("Поиск всех сотрудников по курс с ID: {}", courseId);

        Course course = courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Курс с ID: {} не найдено", courseId);
            throw new IllegalArgumentException("Course not found");
        });

        List<EmployeeResponse> employees = employeeRepository.findAllByCourseId(course.getId());
        log.info("Найдено {} сотрудников с курсом: {}", employees.size(), course.getTitle());

        return employees;
    }

    public List<EmployeeResponse> findAll() {
        log.info("Вывод всех сотрудников ");

        List<EmployeeResponse> employees = employeeRepository.findAllResponse();
        log.info("Найдено {} сотрудников ", employees.size());

        return employees;
    }

    public EmployeeResponse addEmployeeCourse(Long employeeId,
                                              Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException("Not found course")
        );
        Employee employee = employeeRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException("Not found employee")
        );
        log.info("Соединяем сотрудника с ID: {}, с курсом с ID: {}", employeeId, courseId);
        employee.getCourses().add(course);
        employeeRepository.save(employee);
        return findById(employeeId);
    }

    public MessageResponse deleteEmployeeWithCourse(Long employeeId, Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException("Not found course")
        );
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new NotFoundException("Not found employee")
        );
        log.info("Удаляем соеденение сотрудника с ID: {}, с курсом с ID: {}", employeeId, courseId);
        employee.getCourses().remove(course);
        employeeRepository.save(employee);
        return new MessageResponse("Employee deleted with course successfully");
    }

    public EmployeeResponse addImage(Long employeeId,
                                     MultipartFile image) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new NotFoundException("Not found employee")
        );
        employee.setImage(s3FileService.saveImage(image));
        employeeRepository.save(employee);
        return findById(employeeId);
    }
}