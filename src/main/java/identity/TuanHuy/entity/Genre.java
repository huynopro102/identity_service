package identity.TuanHuy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Genre")
@Getter
@Setter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Tên thể loại

    @ManyToMany(mappedBy = "genres")
    private List<Songs> songs; // Liên kết tới bài hát


}
