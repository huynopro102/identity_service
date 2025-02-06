package identity.TuanHuy.controller;
import identity.TuanHuy.dto.request.PostCategoryCreateRequest;
import identity.TuanHuy.entity.PostCategory;
import identity.TuanHuy.service.PostCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postCategories")
@RequiredArgsConstructor
public class PostCategoryController {

    @Autowired
    private final PostCategoryService postCategoryService;

    @PostMapping
    public PostCategory createPostCategory(@RequestBody @Valid PostCategoryCreateRequest request){
        return postCategoryService.createPostCategory(request);
    }

    @GetMapping()
    public List<PostCategory> getAllPostCategory(){
        return postCategoryService.getAllPostCategory();
    }


}
