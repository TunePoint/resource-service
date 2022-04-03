package ua.tunepoint.resource.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import ua.tunepoint.resource.data.entity.Resource;
import ua.tunepoint.resource.service.support.UrlProvider;

@Mapper(componentModel = "spring")
public abstract class ResourceMapper {

    private static final String DOT = ".";

    @Autowired
    private UrlProvider urlProvider;

    @Named("toKey")
    public String toKey(Resource resource) {
        return resource.getId() + DOT + resource.getExtension();
    }

    @Named("resourceUrl")
    protected String resourceUrl(Resource resource) {
        if (resource == null) {
            return null;
        }
        return urlProvider.urlFor(resource);
    }
}
