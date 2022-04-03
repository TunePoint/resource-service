package ua.tunepoint.resource.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ua.tunepoint.resource.data.entity.ImageResource;
import ua.tunepoint.resource.model.response.payload.ImageResourcePayload;

@Mapper(
        componentModel = "spring",
        uses = ResourceMapper.class
)
public interface ImageMapper {

    @Mappings({
            @Mapping(target = "url", source = ".", qualifiedByName = "resourceUrl"),
            @Mapping(target = "width", source = "resolution.width"),
            @Mapping(target = "height", source = "resolution.height")
    })
    ImageResourcePayload toPayload(ImageResource image);
}
