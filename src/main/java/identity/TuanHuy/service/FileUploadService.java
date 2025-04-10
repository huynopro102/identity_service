package identity.TuanHuy.service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import identity.TuanHuy.configuration.CloudinaryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadService {
    private final Cloudinary cloudinary;
    private Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    // constructor handmade
    public FileUploadService(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file , String folder){
        try{
        String publicId = folder + "/" + UUID.randomUUID().toString();
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
    private String determineResourceType(String contentType){
            if(contentType != null){
                if(contentType.startsWith("image/")){
                    return "image";
                }else if (contentType.startsWith("audio/") || contentType.startsWith("video/")){
                    return "video";  // Cloudinary using "video" for audio and video
                }
            }
            return "auto";
    }


    public String uploadImage(MultipartFile file){
        return uploadFile(file , "blogcuahuy/podcasts/images");
    }

}
