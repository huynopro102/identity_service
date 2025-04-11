package identity.TuanHuy.controller;
import identity.TuanHuy.dto.request.EpisodeCreateFormRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.EpisodeResponse;
import identity.TuanHuy.service.EpisodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/episodes")
@RequiredArgsConstructor
public class EpisodeController {
    private final EpisodeService episodeService;


    @GetMapping("/")
    public ApiResponse<List<EpisodeResponse>> getAllEpisodes(){
        return ApiResponse.<List<EpisodeResponse>>builder()
                .code(200)
                .message("get All episodes successfully")
                .result(episodeService.getAllEpisodes())
                .build()
                ;
    }

    @Operation(
            summary = "Create a new Podcast Series",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
    )

    @PostMapping(value = "/" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<EpisodeResponse> createEpisode(@ModelAttribute EpisodeCreateFormRequest episodeCreateFormRequest){
        EpisodeResponse episodeResponse = episodeService.createEpisode(episodeCreateFormRequest);
        return ApiResponse.<EpisodeResponse>builder()
                .code(200)
                .message("create episode successfully")
                .result(episodeResponse)
                .build()
                ;
    }


}
