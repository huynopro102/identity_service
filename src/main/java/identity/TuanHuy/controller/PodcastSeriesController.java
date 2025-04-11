package identity.TuanHuy.controller;


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

    public PodcastSeriesController(PodcastSeriesService podcastSeriesService){
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
            @Parameter(name = "request", required = true, description = "Podcast Series JSON",
                    content = @Content(mediaType = "application/json"))
            @RequestPart("request") @Valid PodcastSeriesRequest request,

            @Parameter(name = "coverImage", required = false, description = "Cover image file",
                    content = @Content(mediaType = "image/jpeg"))
            @RequestPart(value = "coverImage", required = false) MultipartFile coverImage) {

        PodcastSeriesResponse podcastSeriesResponse = podcastSeriesService.createPodcastSeries(request, coverImage);

        return ApiResponse.<PodcastSeriesResponse>builder()
                .code(200)
                .message("create podcastSeries successfully")
                .result(podcastSeriesResponse)
                .build();
    }


    @GetMapping("/")
    public ApiResponse<List<PodcastSeriesResponse>> getAllPodcastsSeries(){
        return ApiResponse.<List<PodcastSeriesResponse>>builder()
                .code(200)
                .message("get All podcastsSeries successfully")
                .result(podcastSeriesService.getAllPodcastSeries())
                .build()
                ;
    }
}
