package identity.TuanHuy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity  // Đánh dấu đây là entity JPA để mapping với database
@Getter  // Lombok tự động tạo các getter methods
@Setter  // Lombok tự động tạo các setter methods
@Table(name = "PostComments")  // Tên bảng trong database
public class PostComments {
    @Id  // Đánh dấu đây là primary key
    @GeneratedValue(strategy = GenerationType.UUID)  // Tự động sinh UUID làm id
    private String id;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    // columnDefinition = "TEXT": Kiểu dữ liệu là TEXT thay vì VARCHAR
    // nullable = false: Không cho phép null
    private String content;  // Nội dung bình luận

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();  // Thời điểm tạo bình luận

    // Quan hệ nhiều-một với Posts: nhiều comment thuộc về một bài đăng
    @ManyToOne(fetch = FetchType.LAZY)  // LAZY loading để tối ưu hiệu suất
    @JoinColumn(name = "post_id", nullable = false)  // Tạo cột post_id, không cho phép null
    private Posts post;  // Bài đăng chứa comment này

    // Quan hệ nhiều-một với Users: nhiều comment được tạo bởi một user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // Tạo cột user_id, không cho phép null
    private Users user;  // Người tạo comment

    // Quan hệ nhiều-một với chính nó: nhiều reply thuộc về một comment cha
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")  // Tạo cột parent_comment_id, cho phép null
    private PostComments parentComment;  // Comment cha (nếu đây là reply)

    // Quan hệ một-nhiều với chính nó: một comment có thể có nhiều reply
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComments> replies;  // Danh sách các reply của comment này
}