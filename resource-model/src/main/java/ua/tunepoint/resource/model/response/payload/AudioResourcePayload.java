package ua.tunepoint.resource.model.response.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AudioResourcePayload {

    private String id;
    private String url;
    private Integer duration;
    private Long size;
    private LocalDateTime created;
    private String originalName;
}
