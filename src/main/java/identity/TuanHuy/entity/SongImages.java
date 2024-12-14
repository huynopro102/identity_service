package identity.TuanHuy.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SongImages")
public class SongImages {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String imageUrl; // Đường dẫn tới ảnh

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Songs song; // Liên kết với Song
}
