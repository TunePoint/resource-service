package ua.tunepoint.resource.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.tunepoint.resource.model.response.ImageGetResponse;

public interface ImageResourceEndpoint {

    @GetMapping("/resource/images/{id}")
    ResponseEntity<ImageGetResponse> getImage(@PathVariable("id") String id);

    @PostMapping("/resource/images")
    ResponseEntity<ImageGetResponse> postImage(@RequestParam("content") MultipartFile content);
}
