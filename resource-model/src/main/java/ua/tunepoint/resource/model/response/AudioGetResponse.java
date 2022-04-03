package ua.tunepoint.resource.model.response;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ua.tunepoint.resource.model.response.payload.AudioResourcePayload;
import ua.tunepoint.web.model.CommonResponse;

@SuperBuilder
@NoArgsConstructor
public class AudioGetResponse extends CommonResponse<AudioResourcePayload> {
}
