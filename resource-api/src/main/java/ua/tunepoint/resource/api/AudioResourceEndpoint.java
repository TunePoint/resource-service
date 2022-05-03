package ua.tunepoint.resource.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.tunepoint.resource.model.response.AudioGetResponse;
import ua.tunepoint.resource.model.response.AudioPostResponse;

public interface AudioResourceEndpoint {

    @GetMapping("/resource/audio/{id}")
    ResponseEntity<AudioGetResponse> getAudio(@PathVariable("id") String id);

    @PostMapping("/resource/audio")
    ResponseEntity<AudioPostResponse> postAudio(@RequestParam("content") MultipartFile content);
}
