package identity.TuanHuy.mapper;

import identity.TuanHuy.dto.response.PodcastSeriesResponse;
import identity.TuanHuy.entity.PodcastSeries;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PodcastSeriesMapper {

    List<PodcastSeriesResponse> toPodcastsSeries(List<PodcastSeries> podcastSeriesList);

    // from entity PodcastSeries to dto PodcastSeriesResponse
    //source: Đây là tên của trường (field) trong đối tượng nguồn (ví dụ, trong Entity) mà bạn muốn ánh xạ.
    //target: Đây là tên của trường trong đối tượng đích (ví dụ, trong DTO) mà bạn muốn gán giá trị từ trường source.
    @Mapping(source = "createdAt", target = "createAt")
    @Mapping(source = "createUpdate" , target = "updateAt")
    PodcastSeriesResponse toPodcastSeriesResponse(PodcastSeries podcastSeries);
}
