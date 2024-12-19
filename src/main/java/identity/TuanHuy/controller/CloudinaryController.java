package identity.TuanHuy.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import identity.TuanHuy.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/cloudinary")
public class CloudinaryController {

    @Autowired
    Cloudinary cloudinary;

    // Upload một hình ảnh
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Upload hình ảnh lên Cloudinary
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            // Trả về thông tin của ảnh đã upload
            return ResponseEntity.ok(uploadResult);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload image: " + e.getMessage());
        }
    }

}
