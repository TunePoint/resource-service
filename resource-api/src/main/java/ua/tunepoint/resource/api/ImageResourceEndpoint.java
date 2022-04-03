package ua.tunepoint.resource.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.tunepoint.resource.model.response.AudioGetResponse;
import ua.tunepoint.resource.model.response.AudioPostResponse;

public interface ImageResourceEndpoint {

    @GetMapping("/images/{id}")
    ResponseEntity<AudioGetResponse> getAudio(String id);

    @PostMapping("/images")
    ResponseEntity<AudioPostResponse> postAudio(@RequestParam("content") MultipartFile content);
}
