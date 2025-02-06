package identity.TuanHuy.service;


import identity.TuanHuy.dto.request.PostCategoryCreateRequest;
import identity.TuanHuy.entity.PostCategory;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.repository.PostCategoryReponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCategoryService {

    @Autowired
    private final PostCategoryReponsitory postCategoryReponsitory;

    @Transactional
    public PostCategory createPostCategory(PostCategoryCreateRequest request){
        System.out.println(request.getName()+"1");
        System.out.println(request.getDescription()+"2");
        if(request.getDescription() == null || request.getName().trim().isEmpty() ){
            System.out.println("Received name: " + request.getName()); // Log để kiểm tra

            throw new AppException(ErrorCode.POST_CATEGORY_NOT_EMPTY);
        }
        PostCategory postCategory = new PostCategory();
        postCategory.setName(request.getName());
        postCategory.setDescription(request.getDescription());
        return postCategoryReponsitory.save(postCategory);
    }

    public List<PostCategory> getAllPostCategory(){
        return postCategoryReponsitory.findAll();
    }
}
