package identity.TuanHuy.controller;

import identity.TuanHuy.dto.request.PostCreationRequest;
import identity.TuanHuy.entity.Posts;
import identity.TuanHuy.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;


    @PostMapping
    Posts CreatePost(@RequestBody @Valid PostCreationRequest request){
            return postService.CreatePost(request);
    }

    @GetMapping
    List<Posts> GetAllPosts(){
        return postService.GetAllPosts();
    }


}
