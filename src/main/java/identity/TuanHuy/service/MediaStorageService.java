package identity.TuanHuy.service;

import org.springframework.web.multipart.MultipartFile;

public interface MediaStorageService {
    String uploadFile(MultipartFile multipartFile , String folder);
    String determineResourceType(String contentType);
    String uploadImage(MultipartFile file, String titlePodcast);
}
