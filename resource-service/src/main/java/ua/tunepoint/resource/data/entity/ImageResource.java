package ua.tunepoint.resource.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImageResource extends Resource {

    private Resolution resolution;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Resolution {

        private Integer height;
        private Integer width;
    }
}
