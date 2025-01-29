package identity.TuanHuy.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // Định nghĩa kiểu dữ liệu là TEXT thay vì VARCHAR
    private String content; // Nội dung bài viết

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt ;

    @Column(name = "is_published")
    private Boolean isPublished = true;


}
