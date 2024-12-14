package identity.TuanHuy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Entity
@Table(name = "Songs")
@Getter
@Setter
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    @Column(nullable = false)
    private String audio; // Đường dẫn tới audio

    @Column(length = 1000)
    private String description; // Mô tả chi tiết

    private String shortDescription; // Mô tả ngắn gọn

    private String author; // Tên tác giả

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // Liên kết với User

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongImages> images; // Danh sách ảnh của bài hát

    @ManyToMany
    @JoinTable(
            name = "song_genres",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres; // Thể loại của bài hát

}
