package kiselev.anton.taskmanager.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;

import java.util.UUID;

/**
 * Задача
 */
@Value
@With
@Builder(toBuilder = true)
public class Task {

    Long id;

    /**
     * Заголовок задачи
     */
    String title;

    /**
     * Автор задачи
     */
    UUID author;

    /**
     * Исполнитель задачи
     */
    UUID assignee;

    /**
     * Статус задачи
     */
    TaskStatus status;

    /**
     * Приоритет задачи
     */
    TaskPriority priority;

    /**
     * Описание задачи
     */
    String description;
}
