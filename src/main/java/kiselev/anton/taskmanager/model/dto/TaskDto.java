package kiselev.anton.taskmanager.model.dto;

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

    Long id;
    String title;
    UUID author;
    UUID assignee;
    TaskStatus status;
    TaskPriority priority;
    String description;
    List<TaskCommentDto> comments;
    Instant created;
    Instant updated;
}
