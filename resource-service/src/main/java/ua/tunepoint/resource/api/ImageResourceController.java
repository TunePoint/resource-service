package ua.tunepoint.resource.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.tunepoint.resource.model.response.ImageGetResponse;
import ua.tunepoint.resource.model.response.ImagePostResponse;
import ua.tunepoint.resource.service.ImageResourceService;

@RestController
@RequestMapping("/resource/images")
@RequiredArgsConstructor
public class ImageResourceController {

    private static final String CONTENT = "content";

    private final ImageResourceService imageService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ImagePostResponse> postAudio(@RequestParam(CONTENT) MultipartFile content) {
        var payload = imageService.save(content);
        var response = ImagePostResponse.builder().payload(payload).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ImageGetResponse> getAudio(@PathVariable String id) {
        var payload = imageService.findById(id);
        var response = ImageGetResponse.builder().payload(payload).build();
        return ResponseEntity.ok(response);
    }
}
