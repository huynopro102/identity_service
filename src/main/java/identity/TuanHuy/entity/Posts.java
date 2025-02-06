package identity.TuanHuy.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column( name = "content", columnDefinition = "TEXT") // Định nghĩa kiểu dữ liệu là TEXT thay vì VARCHAR
    private String content; // Nội dung bài viết

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt ;

    @Column(name = "is_published")
    private Boolean isPublished = true;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private Users user;


    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<PostImages> images;


    @OneToMany(mappedBy = "post" ,  cascade = CascadeType.ALL , orphanRemoval = true)
    private List<PostComments> comments;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<PostReactions> reactions;

    @ManyToMany()
    @JoinTable(
            name = "post_categories" ,
            joinColumns = @JoinColumn(name = "post_id") ,
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<PostCategory> categories;



}
