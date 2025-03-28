package identity.TuanHuy.mapper;

import identity.TuanHuy.dto.response.PodcastSeriesResponse;
import identity.TuanHuy.entity.PodcastSeries;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PodcastSeriesMapper {

    List<PodcastSeriesResponse> toPodcastsSeries(List<PodcastSeries> podcastSeriesList);

    // from entity PodcastSeries to dto PodcastSeriesResponse
    PodcastSeriesResponse toPodcastSeriesResponse(PodcastSeries podcastSeries);
}
