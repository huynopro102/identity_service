package identity.TuanHuy.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import identity.TuanHuy.dto.request.PodcastSeriesRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.PodcastSeriesResponse;
import identity.TuanHuy.service.PodcastSeriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/podcastSeries")
@CrossOrigin(origins = "*")
public class PodcastSeriesController {
    private final PodcastSeriesService podcastSeriesService;

    public PodcastSeriesController(PodcastSeriesService podcastSeriesService) {
        this.podcastSeriesService = podcastSeriesService;
    }


    //MediaType.MULTIPART_FORM_DATA_VALUE để nhận dữ liệu. Điều này có nghĩa là:
    //Header:
    //Content-Type: multipart/form-data
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Create a new Podcast Series",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                    )
            )
    )
    public ApiResponse<PodcastSeriesResponse> createPodcastSeries(
            @Parameter(description = "Podcast Series JSON", required = true)
            @RequestPart("request") String requestJson,

            @Parameter(description = "Cover image file", required = false)
            @RequestPart(value = "coverImage", required = false) MultipartFile coverImage
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PodcastSeriesRequest request = objectMapper.readValue(requestJson, PodcastSeriesRequest.class);

        PodcastSeriesResponse podcastSeriesResponse = podcastSeriesService.createPodcastSeries(request, coverImage);

        return ApiResponse.<PodcastSeriesResponse>builder()
                .code(200)
                .message("create podcastSeries successfully")
                .result(podcastSeriesResponse)
                .build();
    }
}

