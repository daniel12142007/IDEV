package com.example.idevbackend.services;

import com.example.idevbackend.exceptions.NotFoundException;
import com.example.idevbackend.models.Direction;
import com.example.idevbackend.models.enums.Language;
import com.example.idevbackend.payload.request.DirectionRequest;
import com.example.idevbackend.payload.response.DirectionResponse;
import com.example.idevbackend.payload.response.MessageResponse;
import com.example.idevbackend.repositories.DirectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DirectionService {
    private final DirectionRepository directionRepository;

    public DirectionResponse saveDirection(DirectionRequest directionRequest) {
        log.info("Сохранение направления с названием: {}", directionRequest.title());

        if (directionRepository.existsByTitle(directionRequest.title())) {
            log.error("Направление с названием '{}' уже существует", directionRequest.title());
            throw new IllegalArgumentException("Direction title already exists");
        }

        Direction direction = Direction.builder()
                .title(directionRequest.title())
                .build();

        directionRepository.save(direction);
        log.info("Направление успешно сохранено: {}", direction.getId());

        return findById(direction.getId());
    }

    public DirectionResponse updateDirection(Long id, String title) {
        log.info("Обновление направления с ID: {}", id);

        Direction direction = directionRepository.findById(id).orElseThrow(() -> {
            log.error("Направление с ID: {} не найдено", id);
            return new NotFoundException("Not found direction ID: " + id);
        });

        if (directionRepository.existsByTitleAndIdNot(title, id)) {
            log.error("Направление с названием '{}' уже существует для другого ID", title);
            throw new IllegalArgumentException("Direction title already exists");
        }

        direction = Direction.builder()
                .id(direction.getId())
                .title(title)
                .build();

        directionRepository.save(direction);
        log.info("Направление с ID: {} успешно обновлено", id);

        return findById(id);
    }

    public MessageResponse deleteDirection(Long id) {
        log.info("Удаление направления с ID: {}", id);

        Direction direction = directionRepository.findById(id).orElseThrow(() -> {
            log.error("Направление с ID: {} не найдено", id);
            return new NotFoundException("Not found direction ID: " + id);
        });

        directionRepository.delete(direction);
        log.info("Направление с ID: {} успешно удалено", id);

        return new MessageResponse("Direction deleted successfully");
    }

    public DirectionResponse findById(Long id) {
        log.info("Поиск направления с ID: {}", id);

        Direction direction = directionRepository.findById(id).orElseThrow(() -> {
            log.error("Направление с ID: {} не найдено", id);
            return new NotFoundException("Not found direction ID: " + id);
        });

        log.info("Направление с ID: {} найдено", id);
        return new DirectionResponse(direction.getId(), direction.getTitle());
    }

    public List<DirectionResponse> findAll() {
        log.info("Вывод всех направлений");

        List<DirectionResponse> directions = directionRepository.findAllResponseByLanguage();
        log.info("Найдено {} направлений", directions.size());

        return directions;
    }
}
