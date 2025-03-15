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

    private UUID authorId;
    private UUID assigneeId;
    private TaskPriority priority;
    private List<TaskStatus> statuses;
    private Instant createdFrom;
    private Instant createdTo;
}
