
package kiselev.anton.taskmanager.port.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kiselev.anton.taskmanager.domain.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Schema(description = "Запрос на создание задачи")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {

    @Schema(description = "Заголовок задачи")
    private String title;

    @Schema(description = "Автор задачи")
    private UUID author;

    @Schema(description = "Исполнитель задачи")
    private UUID assignee;

    @Schema(description = "Приоритет задачи")
    private TaskPriority priority;

    @Schema(description = "Описание задачи")
    private String description;
}
