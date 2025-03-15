package kiselev.anton.taskmanager.adapter.dao;

import kiselev.anton.taskmanager.common.util.sql.SqlFilters;
import kiselev.anton.taskmanager.domain.TaskPriority;
import kiselev.anton.taskmanager.domain.TaskStatus;
import kiselev.anton.taskmanager.model.dto.TaskDto;
import kiselev.anton.taskmanager.port.request.TaskSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TaskSearchDao {

    private final NamedParameterJdbcOperations jdbc;
    private final RowMapper<TaskDto> taskDtoRowMapper = new TaskDtoRowMapper();

    public List<TaskDto> search(TaskSearchRequest request, int limit, int offset) {

        SqlFilters filters = buildSearchTaskFilter(request);

        String sql = "SELECT * FROM tasks t " + filters.makeWhereClause()
            + " LIMIT " + limit + " OFFSET " + offset;
        return jdbc.query(sql, filters.getParams(), taskDtoRowMapper);
    }

    public int count(TaskSearchRequest request) {

        SqlFilters filters = buildSearchTaskFilter(request);
        String sql = "SELECT COUNT(1) FROM tasks t " + filters.makeWhereClause();
        return jdbc.query(sql, filters.getParams(), (rs, i) -> rs.getInt(1)).get(1);
    }

    private static SqlFilters buildSearchTaskFilter(TaskSearchRequest request) {
        return SqlFilters.builder()
            .eq("author", request.getAuthorId())
            .eq("assignee", request.getAssigneeId())
            .eq("priority", request.getPriority())
            .in("status", request.getStatuses())
            .gt("created", request.getCreatedFrom())
            .lt("created", request.getCreatedTo())
            .build();
    }

    static class TaskDtoRowMapper implements RowMapper<TaskDto> {

        @Override
        public TaskDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return TaskDto.builder()
                .id(rs.getLong("t.id"))
                .title(rs.getString("t.title"))
                .author(UUID.fromString(rs.getString("t.author")))
                .assignee(UUID.fromString(rs.getString("t.assignee")))
                .status(TaskStatus.valueOf(rs.getString("t.status")))
                .priority(TaskPriority.valueOf(rs.getString("t.priority")))
                .description(rs.getString("t.description"))
                .created(rs.getTimestamp("t.created").toInstant())
                .updated(rs.getTimestamp("t.updated").toInstant())
                .build();
        }
    }
}