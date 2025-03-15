package kiselev.anton.taskmanager.service;

import kiselev.anton.taskmanager.adapter.dao.TaskSearchDao;
import kiselev.anton.taskmanager.domain.Task;
import kiselev.anton.taskmanager.model.dto.TaskDto;
import kiselev.anton.taskmanager.port.request.TaskSearchRequest;
import kiselev.anton.taskmanager.port.request.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskSearchService {

    private final TaskSearchDao taskSearchDao;

    public List<TaskDto> search(TaskSearchRequest request, int limit, int offset) {
        return taskSearchDao.search(request, limit, offset);
    }

    public int count(TaskSearchRequest request) {
        return taskSearchDao.count(request);
    }
}