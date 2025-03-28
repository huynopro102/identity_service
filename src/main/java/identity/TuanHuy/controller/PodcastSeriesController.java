package identity.TuanHuy.controller;


import identity.TuanHuy.dto.request.PodcastSeriesRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.PodcastSeriesResponse;
import identity.TuanHuy.service.PodcastSeriesService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/podcastSeries")
@CrossOrigin(origins = "*")
public class PodcastSeriesController {
    private PodcastSeriesService podcastSeriesService;

    public PodcastSeriesController(PodcastSeriesService podcastSeriesService){
        this.podcastSeriesService = podcastSeriesService;
    }

    @PostMapping("/")
    public ApiResponse<PodcastSeriesResponse> createPodcastSeries(@RequestBody PodcastSeriesRequest request){
        PodcastSeriesResponse podcastSeriesResponse = podcastSeriesService.createPodcastSeries(request);
        return ApiResponse.<PodcastSeriesResponse>builder()
                .code(200)
                .message("create podcastSeries successfully")
                .result(podcastSeriesResponse)
                .build()
                ;
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
