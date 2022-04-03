package ua.tunepoint.resource.model.response.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResourcePayload {

    private String id;
    private String url;
    private Integer width;
    private Integer height;
    private Long size;
    private LocalDateTime created;
    private String originalName;
}
