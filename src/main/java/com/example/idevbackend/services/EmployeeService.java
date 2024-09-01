package com.example.idevbackend.services;

import com.example.idevbackend.exceptions.NotFoundException;
import com.example.idevbackend.models.Direction;
import com.example.idevbackend.models.Employee;
import com.example.idevbackend.models.enums.Language;
import com.example.idevbackend.payload.request.EmployeeRequest;
import com.example.idevbackend.payload.response.EmployeeResponse;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.repositories.DirectionRepository;
import com.example.idevbackend.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DirectionRepository directionRepository;

    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest,
                                         Long directionId) {
        log.info("Нахождение направление с ID: {}", directionId);
        Direction direction = directionRepository.findById(directionId).orElseThrow(
                () -> {
                    log.error("Направлние с ID не найден: {}", directionId);
                    return new NotFoundException("Direction not found");
                }
        );

        if (direction.getLanguage() != employeeRequest.language()) {
            log.error("Язык направлении и язык работника не совпадают");
            throw new IllegalArgumentException("The language of the direction and the language of the employee do not match");
        }
        log.info("Сохранение сотрудника с именем: {}", employeeRequest.fullName());

        Employee employee = Employee.builder()
                .fullName(employeeRequest.fullName())
                .image(employeeRequest.image())
                .linkLinkedIn(employeeRequest.linkLinkedIn())
                .language(employeeRequest.language())
                .directions(direction)
                .build();

        employeeRepository.save(employee);
        log.info("Сотрудник успешно сохранен: {}", employee.getId());

        return findById(employee.getId());
    }

    public EmployeeResponse updateEmployee(Long id, String fullName, String image, String linkLinkedIn) {
        log.info("Обновление сотрудника с ID: {}", id);

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error("Сотрудник с ID: {} не найден", id);
            return new NotFoundException("Not found employee ID: " + id);
        });

        employee = Employee.builder()
                .id(employee.getId())
                .fullName(fullName)
                .image(image)
                .linkLinkedIn(linkLinkedIn)
                .language(employee.getLanguage())
                .directions(employee.getDirections())
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
                employee.getLanguage(),
                employee.getDirections().getTitle());
    }

    public List<EmployeeResponse> findAll(Language language) {
        log.info("Поиск всех сотрудников с языком: {}", language);

        List<EmployeeResponse> employees = employeeRepository.findAllResponseByLanguage(language);
        log.info("Найдено {} сотрудников с языком: {}", employees.size(), language);

        return employees;
    }
}
