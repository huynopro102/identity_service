package identity.TuanHuy.service;

import org.springframework.web.multipart.MultipartFile;

public interface MediaStorageService {
    String uploadFile(MultipartFile file , String folder, String numberEpisode);
    String determineResourceType(String contentType);
    String uploadCoverImagePodcast(MultipartFile file , String titlePodcast);
    String uploadEpisode(MultipartFile audio , String nameEpisode);
}
