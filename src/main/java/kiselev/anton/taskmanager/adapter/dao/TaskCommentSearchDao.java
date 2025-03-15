package kiselev.anton.taskmanager.adapter.dao;

import com.example.tradingnotifications.domain.Stock;
import com.example.tradingnotifications.domain.StockType;
import kiselev.anton.taskmanager.model.dto.TaskCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TaskCommentSearchDao {

    private final NamedParameterJdbcOperations jdbc;
    private final RowMapper<TaskCommentDto> rowMapper = new TaskCommentSearchDao.TaskCommentRowMapper();

    public List<Stock> list(Integer limit, Integer offset) {
        String sql = """
                SELECT * FROM stocks
                ORDER BY name
                LIMIT :limit
                OFFSET :offset""";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", limit)
                .addValue("offset", offset);

        return jdbc.query(sql, params, rowMapper);
    }

    static class TaskCommentRowMapper implements RowMapper<TaskCommentDto> {

        @Override
        public TaskCommentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return TaskCommentDto.builder()
                .id(rs.getLong("tc.id"))
                .author(UUID.fromString(rs.getString("tc.author")))
                .body(rs.getString("tc.body"))
                .created(rs.getTimestamp("tc.created").toInstant())
                .updated(rs.getTimestamp("tc.updated").toInstant())
                .build();
        }
    }
}
