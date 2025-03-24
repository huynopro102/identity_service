package identity.TuanHuy.controller;

import identity.TuanHuy.dto.request.ConversionRequest;
import identity.TuanHuy.dto.response.ConversionResponse;
import identity.TuanHuy.service.ConversionService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController
@RequestMapping("/api/v1")
public class YoutubeMp3Controller {
    private final ConversionService conversionService;

    @Autowired
    public YoutubeMp3Controller(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping("/convert")
    public ResponseEntity<ConversionResponse> convertYoutubeToMp3(
            @RequestBody ConversionRequest request) {
        try {
            String mp3Link = conversionService.convertYoutubeToMp3(request.getYoutubeLink(), request.getQuaity());
            return ResponseEntity.ok(new ConversionResponse(mp3Link));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ConversionResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = conversionService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        // Use the actual file name from YouTube for better user experience
        String originalFileName = fileName;
        if (fileName.contains("-")) {
            // Extract video ID from the filename
            originalFileName = fileName.split("-")[0] + ".mp3";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + originalFileName + "\"")
                .body(resource);
    }



    @GetMapping("/health")
    public ResponseEntity<String> checkHealth(){
        return ResponseEntity.ok("server is running");
    }

}
