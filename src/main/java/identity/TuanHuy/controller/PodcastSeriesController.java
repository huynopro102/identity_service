package identity.TuanHuy.controller;

import identity.TuanHuy.dto.request.PodcastSeriesFormRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.PodcastSeriesResponse;
import identity.TuanHuy.service.PodcastSeriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/podcastSeries")
@CrossOrigin(origins = "*")
public class PodcastSeriesController {
    private final PodcastSeriesService podcastSeriesService;

    public PodcastSeriesController(PodcastSeriesService podcastSeriesService) {
        this.podcastSeriesService = podcastSeriesService;
    }

    @GetMapping("/{podcastSeriesId}")
    public ApiResponse<PodcastSeriesResponse> getPodcastSeriesById(@PathVariable String podcastSeriesId){
        PodcastSeriesResponse podcastSeriesResponse = podcastSeriesService.getPodcastSeriesById(podcastSeriesId);
        return ApiResponse.<PodcastSeriesResponse>builder()
                .message("get successfully")
                .code(200)
                .result(podcastSeriesResponse)
                .build()
                ;
    }

    @GetMapping("/")
    public ApiResponse<List<PodcastSeriesResponse>> getAllPodcastSeries(){
        List<PodcastSeriesResponse> podcastSeriesResponseList = podcastSeriesService.getAllPodcastSeries();
        return ApiResponse.<List<PodcastSeriesResponse>>builder()
                .message("get PodcastSeries successfully")
                .code(200)
                .result(podcastSeriesResponseList)
                .build();

    }

    //MediaType.MULTIPART_FORM_DATA_VALUE để nhận dữ liệu. Điều này có nghĩa là:
    //Header:
    //Content-Type: multipart/form-data
    // Endpoint cho việc tạo Podcast Series với form-data gồm các field và 1 file ảnh
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
            @Parameter(description = "Podcast Series Form Data", required = true)
            @Valid @ModelAttribute PodcastSeriesFormRequest formRequest
    ) {
        PodcastSeriesResponse podcastSeriesResponse = podcastSeriesService.createPodcastSeries(formRequest);
        return ApiResponse.<PodcastSeriesResponse>builder()
                .code(200)
                .message("Create Podcast Series successfully")
                .result(podcastSeriesResponse)
                .build();
    }
}

