package com.example.idevbackend.api;

import com.example.idevbackend.models.enums.Language;
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

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Создание нового сотрудника",
            description = "Добавление нового сотрудника в систему с указанием полного имени и других данных")
    public EmployeeResponse saveEmployee(@RequestBody EmployeeRequest employeeRequest,
                                         @RequestParam Long directionId) {
        return employeeService.saveEmployee(employeeRequest, directionId);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Обновление существующего сотрудника",
            description = "Обновление информации о сотруднике по идентификатору")
    public EmployeeResponse updateEmployee(@PathVariable Long id,
                                           @RequestParam String fullName,
                                           @RequestParam String image,
                                           @RequestParam String linkLinkedIn) {
        return employeeService.updateEmployee(id, fullName, image, linkLinkedIn);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Удаление сотрудника",
            description = "Удаление сотрудника по идентификатору")
    public MessageResponse deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение сотрудника по ID",
            description = "Получение информации о сотруднике по его идентификатору")
    public EmployeeResponse findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("find/all")
    @Operation(summary = "Получение всех сотрудников по языку",
            description = "Получение списка всех сотрудников, фильтрованных по языку")
    public List<EmployeeResponse> findAll(@RequestParam Language language) {
        return employeeService.findAll(language);
    }
}