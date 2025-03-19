package kiselev.anton.taskmanager.domain;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.Instant;
import java.util.UUID;

/**
 * Комментарий к задаче.
 */
@Value
@With
@Builder(toBuilder = true)
public class TaskComment {

    @Nullable
    Long id;

    Long taskId;

    /**
     * Дата создания комментария
     */
    Instant created;

    /**
     * Дата изменения комментария
     */
    @Nullable
    Instant updated;

    /**
     * Автор комментария
     */
    UUID author;

    /**
     * Тело комментария
     */
    String body;
}
