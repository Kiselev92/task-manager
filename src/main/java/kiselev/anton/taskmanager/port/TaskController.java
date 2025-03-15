package kiselev.anton.taskmanager.port;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kiselev.anton.taskmanager.common.util.sql.ListingDto;
import kiselev.anton.taskmanager.common.util.sql.ListingDto.MetaDto;
import kiselev.anton.taskmanager.model.dto.TaskDto;
import kiselev.anton.taskmanager.port.request.CreateTaskRequest;
import kiselev.anton.taskmanager.port.request.TaskSearchRequest;
import kiselev.anton.taskmanager.port.request.UpdateTaskRequest;
import kiselev.anton.taskmanager.service.TaskSearchService;
import kiselev.anton.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "API для работы с задачами")
@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskSearchService taskSearchService;

    @Operation(description = "Создать задачу")
    @PostMapping
    public Long create(@RequestBody CreateTaskRequest request) {
        return taskService.create(request);
    }

    @Operation(description = "Получить задачу по id")
    @GetMapping("/{id}")
    public TaskDto getNotification(@PathVariable("id") Long id) {
        return taskService.findById(id, role, userId);
    }


    @GetMapping
    public ListingDto<TaskDto> search(
        TaskSearchRequest request,
        @RequestParam(defaultValue = "10") int limit,
        @RequestParam(defaultValue = "0") int offset
    ) {
        List<TaskDto> result = taskSearchService.search(request, limit, offset);
        return toListingDto(request, result, limit, offset);
    }

    @Operation(description = "Изменить задачу")
    @PutMapping
    public void updateNotification(@RequestBody UpdateTaskRequest request) {
        taskService.update(request, role, userId);
    }

    @Operation(description = "Удалить задачу по id")
    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable("id") Long id) {
        taskService.deleteById(id, role, userId);
    }

    private ListingDto<TaskDto> toListingDto(TaskSearchRequest request, List<TaskDto> tasks, int limit, int offset) {
        return new ListingDto<>(
            tasks,
            MetaDto.builder()
                .limit(limit)
                .offset(offset)
                .count(taskSearchService.count(request))
                .build()
        );
    }
}