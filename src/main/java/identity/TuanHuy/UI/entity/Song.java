package identity.TuanHuy.UI.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column
    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author; // Mỗi bài hát có một tác giả

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private MediaFile media; // Mỗi bài hát có một media liên kết (như audio hoặc video)

}
