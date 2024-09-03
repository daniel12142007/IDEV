package com.example.idevbackend.api;

import com.example.idevbackend.payload.request.EmployeeRequest;
import com.example.idevbackend.payload.response.EmployeeResponse;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employee")
public class EmployeeApi {
    private final EmployeeService employeeService;

    @PostMapping("save/{directionId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Создание нового сотрудника",
            description = "Добавление нового сотрудника в систему с указанием направления")
    public EmployeeResponse saveEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable Long directionId) {
        return employeeService.saveEmployee(employeeRequest, directionId);
    }

    @PostMapping("add/employee/{employeeId}/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Добавление сотрудника к курсу",
            description = "Дабавление уже существуещего сотрудника к курсу")
    public EmployeeResponse addEmployee(@PathVariable Long employeeId, @PathVariable Long courseId) {
        return employeeService.addEmployeeCourse(employeeId, courseId);
    }

    @PutMapping("update/{id}/{directionId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Обновление существующего сотрудника",
            description = "Обновление информации о сотруднике по идентификатору и направлению")

    public EmployeeResponse updateEmployee(@PathVariable Long id,
                                           @RequestBody EmployeeRequest employeeRequest,
                                           @PathVariable Long directionId) {
        return employeeService.updateEmployee(id, employeeRequest, directionId);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Удаление сотрудника",
            description = "Удаление сотрудника по идентификатору")
    public MessageResponse deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @DeleteMapping("delete/{employeeId}/with/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Удаление сотрудника с курса",
            description = "Удаляем связку между сотрудников и курсом")
    public MessageResponse deleteEmployee(@PathVariable Long employeeId, @PathVariable Long courseId) {
        return employeeService.deleteEmployeeWithCourse(employeeId, courseId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение сотрудника по ID",
            description = "Получение информации о сотруднике по его идентификатору")
    public EmployeeResponse findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("find/all")
    @Operation(summary = "Получение всех сотрудников",
            description = "Получение списка всех сотрудников")
    public List<EmployeeResponse> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("find/by-direction/{directionId}")
    @Operation(summary = "Получение сотрудников по направлению",
            description = "Получение списка всех сотрудников по заданному направлению")
    public List<EmployeeResponse> findAllByDirection(@PathVariable Long directionId) {
        return employeeService.findAllByDirection(directionId);
    }

    @GetMapping("find/by-course/{courseId}")
    @Operation(summary = "Получение сотрудников по курсу",
            description = "Получение списка всех сотрудников по заданному курсу")
    public List<EmployeeResponse> findAllByCourse(@PathVariable Long courseId) {
        return employeeService.findAllByCourse(courseId);
    }
}