package kiselev.anton.taskmanager.common.util.sql;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Value
public class ListingDto<T> {

    private List<T> results;
    private MetaDto meta;

    @Value
    @Builder
    public static class MetaDto {
        long count;
        long offset;
        long limit;
    }
}
