package ua.tunepoint.resource.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.tunepoint.resource.data.entity.AudioResource;
import ua.tunepoint.resource.data.mapper.AudioMapper;
import ua.tunepoint.resource.data.mapper.ResourceMapper;
import ua.tunepoint.resource.data.repository.AudioResourceRepository;
import ua.tunepoint.resource.model.response.payload.AudioResourcePayload;
import ua.tunepoint.resource.service.converter.AudioConverter;
import ua.tunepoint.web.exception.BadRequestException;
import ua.tunepoint.web.exception.NotFoundException;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ua.tunepoint.resource.utils.FileUtils.persistTemporary;

@Service
@RequiredArgsConstructor
@Slf4j
public class AudioResourceService implements ResourceService<AudioResourcePayload> {

    private final StorageService storageService;
    private final AudioResourceRepository audioRepository;
    private final AudioConverter audioConverter;

    private final AudioMapper audioMapper;

    @Override
    public AudioResourcePayload save(MultipartFile multipart) {

        authorizeContentType(multipart.getContentType());

        File temporaryFile = persistTemporary(multipart);

        AudioResource resource = audioConverter.convert(multipart, temporaryFile);

        var duplicateOptional = findDuplicate(resource);
        if (duplicateOptional.isPresent()) {
            return audioMapper.toPayload(duplicateOptional.get());
        }

        storageService.save(temporaryFile, resource.getStorageKey());

        var savedResource = audioRepository.save(resource);

        return audioMapper.toPayload(savedResource);
    }

    @Override
    public AudioResourcePayload findById(String id) {
        return audioRepository.findById(id).map(audioMapper::toPayload)
                .orElseThrow(() -> new NotFoundException("Audio with id " + id + " was not found"));
    }

    private Optional<AudioResource> findDuplicate(AudioResource resource) {
        List<AudioResource> resources = audioRepository.findByHash(resource.getMd5Hash());
        return resources.stream()
                .filter(it -> Objects.equals(resource.getSize(), it.getSize()))
                .findFirst();
    }

    private void authorizeContentType(String contentType) {
        if (contentType == null || !contentType.startsWith("audio/")) {
            throw new BadRequestException("Invalid file format");
        }
    }
}
