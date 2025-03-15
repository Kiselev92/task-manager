package kiselev.anton.taskmanager.common.util.sql;

import com.google.common.collect.ImmutableList;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Collection;
import java.util.UUID;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Value
public class SqlFilters {

    SqlParameterSource params;
    String predicate;

    private SqlFilters(SqlParameterSource params, String predicate) {
        this.params = params;
        this.predicate = predicate;
    }

    public boolean isEmpty() {
        return StringUtils.isBlank(predicate);
    }

    public String makeWhereClause() {
        return StringUtils.isNotEmpty(predicate) ? "\nWHERE " + predicate : "";
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Простой сборщик предиката для фильтрации строк в таблице.
     * Между выражениями возможен только оператор AND.
     */
    public static class Builder {

        private final MapSqlParameterSource params = new MapSqlParameterSource();
        private final ImmutableList.Builder<String> predicateBuilder = ImmutableList.builder();

        private Builder() {
        }

        public Builder eq(String columnName, Number value) {
            if (null != value) {
                addPredicateAndParameterForEq(columnName, value);
            }
            return this;
        }

        private void addPredicateAndParameterForEq(String columnName, Object value) {
            var paramName = columnName + "Eq";
            predicateBuilder.add(columnName + " = " + param(paramName));
            params.addValue(paramName, value);
        }

        private String param(String columnName) {
            return ":" + columnName;
        }

        public Builder eq(String columnName, Boolean value) {
            if (null != value) {
                addPredicateAndParameterForEq(columnName, value);
            }
            return this;
        }

        public Builder eq(String columnName, String value) {
            if (isNotBlank(value)) {
                addPredicateAndParameterForEq(columnName, value);
            }
            return this;
        }

        public Builder eq(String columnName, UUID value) {
            if (value != null) {
                addPredicateAndParameterForEq(columnName, value.toString());
            }
            return this;
        }

        public Builder eq(String columnName, Enum<?> value) {
            if (value != null) {
                addPredicateAndParameterForEq(columnName, value.toString());
            }
            return this;
        }

        public <T> Builder in(String columnName, Collection<T> values) {
            if (isNotEmpty(values)) {
                var paramName = columnName + "In";
                predicateBuilder.add(columnName + " IN(" + param(paramName) + ")");
                addParameterValuesForInOperator(paramName, values);
            }
            return this;
        }

        private <T> void addParameterValuesForInOperator(String paramName, Collection<T> values) {
            if (values.toArray()[0] instanceof Number) {
                params.addValue(paramName, values);
            } else {
                var valuesString = values.stream()
                    .map(String::valueOf)
                    .toList();
                params.addValue(paramName, valuesString);
            }
        }

        public Builder gt(String columnName, Object value) {
            if (null != value) {
                var paramName = columnName + "Gt";
                predicateBuilder.add(columnName + " > " + param(paramName));
                params.addValue(paramName, value);
            }
            return this;
        }

        public Builder lt(String columnName, Object value) {
            if (null != value) {
                var paramName = columnName + "Lt";
                predicateBuilder.add(columnName + " < " + param(paramName));
                params.addValue(paramName, value);
            }
            return this;
        }

        public SqlFilters build() {
            var predicate = String.join(" AND ", predicateBuilder.build());
            return new SqlFilters(params, predicate);
        }
    }
}
