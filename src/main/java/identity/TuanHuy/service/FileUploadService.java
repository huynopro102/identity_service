package identity.TuanHuy.service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import identity.TuanHuy.configuration.CloudinaryConfig;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadService implements MediaStorageService{
    @Autowired
    private Cloudinary cloudinary;
    private Logger logger = LoggerFactory.getLogger(FileUploadService.class);


    private final String[] WHITE_LIST_IMAGE = {
            "image/gif",
            "image/jpeg",
            "image/png",
            "image/tiff",
            "image/vnd.microsoft.icon",
            "image/x-icon",
            "image/vnd.djvu",
            "image/svg+xml"
    };
    private final String[] WHITE_LIST_AUDIO = {
            "audio/mpeg",
            "audio/x-ms-wma",
            "audio/vnd.rn-realaudio",
            "audio/x-wav",
    };



    // just about IMAGE COVER EPISODE
    // just about AUDIO EPISODE
    // hàm này là hàm final chỉ nhận " file và folder và số tập podcast"
    public String uploadFile(MultipartFile file , String folder, String numberEpisode){
        try{
            logger.info("upload 3 parameter");
        String publicId = folder + "/"+numberEpisode+"-" + UUID.randomUUID().toString();
        logger.info("this is a MINE for file selected "+file.getContentType());
        logger.info("this is a publicID for file selected : "+publicId);
        Map<?,?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "public_id",publicId,
                        "resource_type", determineResourceType(file.getContentType())
                )
        );
        return (String) uploadResult.get("secure_url");
        }catch (IOException ioException){
            throw new RuntimeException("khong the upload len cloudinary ",ioException);
        }
    }

    // just about for upload IMAGE COVER PODCAST
    public String uploadFile(MultipartFile file , String folder){
        try{
            logger.info("upload 2 parameter");
            String publicId = folder + "/coverImagePodcast-" + UUID.randomUUID().toString();
            logger.info("this is a MINE for file selected "+file.getContentType());
            logger.info("this is a publicID for file selected : "+publicId);
            Map<?,?> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "public_id",publicId,
                            "resource_type", determineResourceType(file.getContentType())
                    )
            );
            return (String) uploadResult.get("secure_url");
        }catch (IOException ioException){
            throw new RuntimeException("khong the upload len cloudinary ",ioException);
        }
    }

    // hàm này dùng để xác định loại file đó là gì ví dụ file word , pdf , video , image ,jpg ....
    // contentType là 1 String : MINE (ví dụ: "image/jpeg", "audio/mp3", "video/mp4"...)
    public String determineResourceType(String contentType){
            if(contentType != null){
                if(contentType.startsWith("image/")){
                    return "image";
                }else if (contentType.startsWith("audio/") || contentType.startsWith("video/")){
                    return "video";  // Cloudinary using "video" for audio and video
                }
            }
            return "auto";
    }


    public String uploadCoverImagePodcast(MultipartFile file , String titlePodcast){
        return uploadFile(file , "blogcuahuy/podcasts/"+ titlePodcast );
    }

    @Override
    public String uploadEpisode(MultipartFile audio, String nameEpisode) {
        return "";
    }


    public String uploadAudioEpisode(MultipartFile audioEpisode , String titlePodcast,int numberEpisode){
        boolean isCoverImage = java.util.Arrays.stream(WHITE_LIST_AUDIO)
                .anyMatch(audio -> audio.equalsIgnoreCase(audioEpisode.getContentType()));
        if(!isCoverImage){
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        String folder = "blogcuahuy/podcasts/" + titlePodcast + "/episodes";
        return uploadFile(audioEpisode,folder,numberEpisode+"");
    }

    // Phương thức upload riêng cho cover image của episode
    public String uploadCoverImageEpisode(MultipartFile coverImageEpisode , String titlePodcast , int numberEpisode){
        // kiểm tra ảnh hợp lệ trong white_list
        boolean isImage = java.util.Arrays.stream(WHITE_LIST_IMAGE)
                .anyMatch(type -> type.equalsIgnoreCase(coverImageEpisode.getContentType()));
        if(!isImage){
            throw new AppException(ErrorCode.UPLOAD_FILE_IMAGE_INVALID_CONTENT_TYPE);
        }
        String folder = "blogcuahuy/podcasts/" + titlePodcast + "/images";
        return uploadFile(coverImageEpisode,folder,numberEpisode+"");
    }


}
