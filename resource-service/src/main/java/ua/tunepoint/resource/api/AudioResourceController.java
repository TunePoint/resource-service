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
import ua.tunepoint.resource.model.response.AudioGetResponse;
import ua.tunepoint.resource.model.response.AudioPostResponse;
import ua.tunepoint.resource.service.AudioResourceService;

@RestController
@RequestMapping("/resource/audio")
@RequiredArgsConstructor
public class AudioResourceController {

    private static final String CONTENT = "content";

    private final AudioResourceService audioService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AudioPostResponse> postAudio(@RequestParam(CONTENT) MultipartFile content) {
        var payload = audioService.save(content);
        var response = AudioPostResponse.builder().payload(payload).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AudioGetResponse> getAudio(@PathVariable String id) {
        var payload = audioService.findById(id);
        var response = AudioGetResponse.builder().payload(payload).build();
        return ResponseEntity.ok(response);
    }
}
