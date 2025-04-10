package identity.TuanHuy.controller;


import identity.TuanHuy.dto.request.PodcastSeriesRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.PodcastSeriesResponse;
import identity.TuanHuy.service.PodcastSeriesService;
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



    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<PodcastSeriesResponse> createPodcastSeries
            (@RequestBody PodcastSeriesRequest request ,
             @RequestPart(value = "coverImage",required = false) MultipartFile coverImage ){
        PodcastSeriesResponse podcastSeriesResponse = podcastSeriesService.createPodcastSeries(request,coverImage);

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
