package identity.TuanHuy.service;


import identity.TuanHuy.dto.request.PostImagesCreateRequest;
import identity.TuanHuy.entity.PostImages;
import identity.TuanHuy.entity.Posts;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.repository.PostImageReponsitory;
import identity.TuanHuy.repository.PostReponsitory;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping("ui/postImages")
public class PostImageService {

    @Autowired
    private final PostImageReponsitory postImageReponsitory;

    @Autowired
    private final PostReponsitory postReponsitory;


    @Transactional
    public PostImages createPostImage(PostImagesCreateRequest request){

        Posts post = postReponsitory.findById(request.getPostId())
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        PostImages postImages = new PostImages();
        postImages.setImageUrl(request.getImageUrl());
        postImages.setImageDescription(request.getImageDescription());
        postImages.setPost(post);

        return postImageReponsitory.save(postImages);

    }


    public List<PostImages> getImagesByPostId(String postId){
        return postImageReponsitory.findByPostId(postId);
    }


}
