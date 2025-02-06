package identity.TuanHuy.service;

import identity.TuanHuy.dto.request.PostCreationRequest;
import identity.TuanHuy.entity.Posts;
import identity.TuanHuy.entity.Users;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.repository.PostReponsitory;
import identity.TuanHuy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostReponsitory postReponsitory;

    @Autowired
    private UserRepository userRepository;

    public Posts CreatePost(PostCreationRequest request){
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Posts post = new Posts();

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUser(user);
        post.setIsPublished(request.getIsPublished());

        return postReponsitory.save(post);
    }

    public List<Posts> GetAllPosts(){
        return postReponsitory.findAll();
    }



}
