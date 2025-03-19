package kiselev.anton.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kiselev.anton.taskmanager.domain.TaskPriority;
import kiselev.anton.taskmanager.domain.TaskStatus;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Value
@With
@Builder
public class TaskDto {

    @Schema(description = "ID задачи")
    Long id;

    @Schema(description = "Заголовок задачи")
    String title;

    @Schema(description = "Автор задачи")
    UUID author;

    @Schema(description = "Исполнитель задачи")
    UUID assignee;

    @Schema(description = "Статус задачи")
    TaskStatus status;

    @Schema(description = "Приоритет задачи")
    TaskPriority priority;

    @Schema(description = "Описание задачи")
    String description;

    @Schema(description = "Комментарии к задаче")
    List<TaskCommentDto> comments;

    @Schema(description = "Дата создания комментария")
    Instant created;

    @Schema(description = "Дата редактирования комментария")
    Instant updated;
}
