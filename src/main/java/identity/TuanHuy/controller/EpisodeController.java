package identity.TuanHuy.controller;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.EpisodeResponse;
import identity.TuanHuy.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
