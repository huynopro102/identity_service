package identity.TuanHuy.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PodcastSeriesResponse implements Serializable {
     private String id;
     private String author;
     private String coverUrl;
     private String description;
     private String narrator;
     private String title;
     private int totalDuration;
     private LocalDateTime createAt;
     private LocalDateTime updateAt;
}