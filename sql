package identity.TuanHuy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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
----------------------------------------------------------------------------------
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
----------------------------------------------------------------------------------
package identity.TuanHuy.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Constructors, Getters, and Setters
    public Genre() {}

}
----------------------------------------------------------------------------------


