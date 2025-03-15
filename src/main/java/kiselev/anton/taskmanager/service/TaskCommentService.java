package kiselev.anton.taskmanager.service;

import com.example.tradingcomments.domain.comment;
import kiselev.anton.taskmanager.port.request.CreateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskCommentService {

    private final CommentDao commentDao;

    public void create(CreateTaskRequest request) {
        return request.create(request);
    }
}