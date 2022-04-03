package ua.tunepoint.resource.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.tunepoint.resource.data.entity.ImageResource;
import ua.tunepoint.resource.data.mapper.ImageMapper;
import ua.tunepoint.resource.data.mapper.ResourceMapper;
import ua.tunepoint.resource.data.repository.ImageResourceRepository;
import ua.tunepoint.resource.model.response.payload.ImageResourcePayload;
import ua.tunepoint.resource.service.converter.ImageConverter;
import ua.tunepoint.web.exception.BadRequestException;
import ua.tunepoint.web.exception.NotFoundException;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ua.tunepoint.resource.utils.FileUtils.persistTemporary;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageResourceService implements ResourceService<ImageResourcePayload> {

    private final ImageConverter imageProcessor;
    private final ImageResourceRepository imageRepository;
    private final StorageService storageService;

    private final ImageMapper imageMapper;
    private final ResourceMapper resourceMapper;

    @Override
    public ImageResourcePayload save(MultipartFile multipart) {

        authorizeContentType(multipart.getContentType());

        File persisted = persistTemporary(multipart);

        ImageResource resource = imageProcessor.convert(multipart, persisted);

        var duplicateOptional = findDuplicate(resource);
        if (duplicateOptional.isPresent()) {
            return imageMapper.toPayload(duplicateOptional.get());
        }

        storageService.save(persisted, resource.getStorageKey());

        var savedResource = imageRepository.save(resource);

        return imageMapper.toPayload(savedResource);
    }

    @Override
    public ImageResourcePayload findById(String id) {
        return imageRepository.findById(id).map(imageMapper::toPayload)
                .orElseThrow(() -> new NotFoundException("Image with id " + id + " was not found"));
    }

    private Optional<ImageResource> findDuplicate(ImageResource resource) {
        List<ImageResource> resources = imageRepository.findByHash(resource.getMd5Hash());
        return resources.stream()
                .filter(it -> Objects.equals(resource.getSize(), it.getSize()))
                .findFirst();
    }

    private void authorizeContentType(String contentType) {
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new BadRequestException("Invalid file format");
        }
    }
}
