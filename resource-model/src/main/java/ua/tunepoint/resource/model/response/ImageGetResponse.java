package ua.tunepoint.resource.model.response;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ua.tunepoint.resource.model.response.payload.ImageResourcePayload;
import ua.tunepoint.web.model.CommonResponse;

@SuperBuilder
@NoArgsConstructor
public class ImageGetResponse extends CommonResponse<ImageResourcePayload> {
}
