package kiselev.anton.taskmanager.service;

import kiselev.anton.taskmanager.adapter.dao.TaskDao;
import kiselev.anton.taskmanager.domain.Task;
import kiselev.anton.taskmanager.domain.UserRole;
import kiselev.anton.taskmanager.port.request.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskDao taskDao;

    public void create(Task task) { return taskDao.create(task); }

    public Task findById(Long id, UserRole role, Long userId) { return taskDao.findById(id) }

    public void deleteById(Long id, UserRole role, Long userId) { taskDao.deleteById(id); }

    public void update(UpdateTaskRequest request, UserRole role, Long userId) {
        Long id = newTask.getId();
        if (id == null) {
            throw new IllegalStateException("Не указан идентификатор обновляемого уведомления. " + newTask);
        }
        Task oldTask = taskDao.findById(id);
        if (oldTask == null) {
            throw new NoSuchElementException("task with id " + id + " for update not found");
        }
        taskDao.update(id, newTask);
    }
}