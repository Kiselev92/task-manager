package kiselev.anton.taskmanager.adapter.dao;

import kiselev.anton.taskmanager.domain.Task;
import kiselev.anton.taskmanager.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TaskDao {

    private final NamedParameterJdbcOperations jdbc;
    private final RowMapper<Task> taskRowMapper = new BeanPropertyRowMapper<>();

    public Long create(Task task) {
        String sql = """
                INSERT INTO tasks (id, created, updated, title, status, author, assignee, priority, description)
                VALUES (default, :created, :updated, :title, :status, :author, :assignee, :priority, :description)
                """;

        Instant now = Instant.now();
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", task.getId())
                .addValue("created", Timestamp.from(now))
                .addValue("updated", Timestamp.from(now))
                .addValue("title", task.getTitle())
                .addValue("status", task.getStatus().name())
                .addValue("author", task.getAuthor())
                .addValue("assignee", task.getAssignee())
                .addValue("priority", task.getPriority().name());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(sql, params, keyHolder);
        return (Long) keyHolder.getKeys().get("id");
    }

    public Task findById(Long id, UserRole role, Long userId) {
        String sql = """
                SELECT * FROM tasks t
                LEFT JOIN task_comments tc ON tc.task_id = t.id  
                WHERE t.id = :id""";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("role", role)
                .addValue("userId", userId);
        List<Task> results = jdbc.query(sql, params, taskRowMapper);
        return results.isEmpty() ? null : results.get(0);
    }

    public void deleteById(Long id) {
        jdbc.update("DELETE FROM tasks WHERE id = :id", Map.of("id", id));
    }

    public void update(Long id, Task task) {
        String sql = """
                UPDATE tasks 
                SET updated = :updated, title = :title, status = :status, author = :author, assignee = :assignee, 
                priority = :priority, description = :description
                WHERE id = :id""";

        SqlParameterSource params = new MapSqlParameterSource("id", id)
                .addValue("updated", Timestamp.from(Instant.now()))
                .addValue("title", task.getTitle())
                .addValue("status", task.getStatus().name())
                .addValue("author", task.getAuthor())
                .addValue("assignee", task.getAssignee())
                .addValue("priority", task.getPriority().name())
                .addValue("description", task.getDescription());

        jdbc.update(sql, params);
    }
}