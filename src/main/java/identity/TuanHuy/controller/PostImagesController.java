package identity.TuanHuy.controller;

import com.cloudinary.Api;
import identity.TuanHuy.dto.reponse.ApiResponse;
import identity.TuanHuy.dto.request.PostImagesCreateRequest;
import identity.TuanHuy.entity.PostImages;
import identity.TuanHuy.service.PostImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/postImages")
@RequiredArgsConstructor
public class PostImagesController {

    private final PostImageService postImageService;


    @PostMapping
    public ApiResponse<PostImages> createPostImage(@RequestBody PostImagesCreateRequest request){
        PostImages savedImage = postImageService.createPostImage(request);
        return ApiResponse.<PostImages>builder()
                .message("image update successfully")
                .code(200)
                .result(savedImage)
                .build();
    }

}
