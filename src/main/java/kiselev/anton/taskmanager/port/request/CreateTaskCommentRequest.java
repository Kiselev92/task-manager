package kiselev.anton.taskmanager.port.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Запрос на создание комментария")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskCommentRequest {

    @Schema(description = "ID задачи, к которой оставляем комментарий")
    private Long taskId;

    @Schema(description = "Тело комментария")
    private String body;
}
