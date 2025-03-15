package kiselev.anton.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Комментарий к задаче")
@Value
@Builder
public class TaskCommentDto {

    @Schema(description = "ID комментария")
    Long id;

    @Schema(description = "Дата создания комментария")
    Instant created;

    @Schema(description = "Дата последнего изменения комментария")
    Instant updated;

    @Schema(description = "Автор комментария")
    UUID author;

    @Schema(description = "Тело комментария")
    String body;
}

