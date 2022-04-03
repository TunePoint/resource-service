package ua.tunepoint.resource.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ua.tunepoint.resource.data.entity.AudioResource;
import ua.tunepoint.resource.model.response.payload.AudioResourcePayload;

@Mapper(
        componentModel = "spring",
        uses = ResourceMapper.class
)
public interface AudioMapper {

    @Mappings({
            @Mapping(target = "url", source = ".", qualifiedByName = "resourceUrl")
    })
    AudioResourcePayload toPayload(AudioResource audio);
}
