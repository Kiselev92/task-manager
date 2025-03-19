package kiselev.anton.taskmanager.port.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kiselev.anton.taskmanager.domain.TaskPriority;
import kiselev.anton.taskmanager.domain.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Schema(description = "Фильтры для поиска задач")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchRequest {

    @Schema(description = "Автор задачи")
    private UUID authorId;

    @Schema(description = "Исполнитель задачи")
    private UUID assigneeId;

    @Schema(description = "Приоритет задачи")
    private TaskPriority priority;

    @Schema(description = "Статус задачи")
    private List<TaskStatus> statuses;

    @Schema(description = "Дата создания задачи")
    private Instant createdFrom;

    @Schema(description = "Дата изменения задачи")
    private Instant createdTo;
}
