package com.example.idevbackend.api;

import com.example.idevbackend.payload.request.DirectionRequest;
import com.example.idevbackend.payload.response.DirectionResponse;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.services.DirectionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/direction")
public class DirectionApi {
    private final DirectionService directionService;

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Создание нового направления",
            description = "Добавление нового направления в систему с указанием названия")
    public DirectionResponse saveDirection(@RequestBody DirectionRequest directionRequest) {
        return directionService.saveDirection(directionRequest);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Обновление существующего направления",
            description = "Обновление информации о направлении по идентификатору")
    public DirectionResponse updateDirection(@PathVariable Long id, @RequestParam String title) {
        return directionService.updateDirection(id, title);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Удаление направления",
            description = "Удаление направления по идентификатору")
    public MessageResponse deleteDirection(@PathVariable Long id) {
        return directionService.deleteDirection(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение направления по ID",
            description = "Получение информации о направлении по его идентификатору")
    public DirectionResponse findById(@PathVariable Long id) {
        return directionService.findById(id);
    }

    @GetMapping("find/all")
    @Operation(summary = "Получение всех направлений ",
            description = "Получение списка всех направлений")
    public List<DirectionResponse> findAll() {
        return directionService.findAll();
    }
}