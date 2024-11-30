package identity.TuanHuy.UI.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "media")
// lưu ý phải đổi lại tên của class Media thành MediaFile để tránh bị trùng package với java
public class MediaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "upload_date")
    private Date uploadDate;

    @OneToOne(mappedBy = "media")
    private Song song; // Một media có thể liên kết với một bài hát (song)

    public enum MediaType {
        IMAGE, AUDIO, VIDEO
    }

    // Getters and Setters
}
