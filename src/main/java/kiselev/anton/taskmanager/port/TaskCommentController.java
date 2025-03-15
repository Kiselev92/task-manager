package kiselev.anton.taskmanager.port;

import com.example.tradingnotifications.domain.Notification;
import com.example.tradingnotifications.port.request.NotificationEditRequest;
import com.example.tradingnotifications.service.NotificationService;
import kiselev.anton.taskmanager.port.request.CreateTaskCommentRequest;
import kiselev.anton.taskmanager.service.TaskCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task/comment")
public class TaskCommentController {

    private final TaskCommentService taskCommentService;

    /**
     * Добавить комментарий к задаче
     * @param request - запрос на добавление комментария
     */
    @PostMapping
    public Long create(@RequestBody CreateTaskCommentRequest request) {
        return taskCommentService.create(request);
    }
}