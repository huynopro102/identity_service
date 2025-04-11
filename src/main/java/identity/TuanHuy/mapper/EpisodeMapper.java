package identity.TuanHuy.mapper;


import identity.TuanHuy.dto.request.EpisodeRequest;
import identity.TuanHuy.dto.response.EpisodeResponse;
import identity.TuanHuy.entity.Episode;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EpisodeMapper {

    // convert from 'entity Episode' to 'EpisodeResponse Dto' similar convert from EpisodeRequest DTO to Episode entity
//    public static EpisodeResponse toEpisodeResponse(Episode episode){
//        if(episode.equals(null)){
//            return null;
//        }
//        EpisodeResponse episodeResponse = new EpisodeResponse();
//
//        episodeResponse.setNameEpisode(episode.getNameEpisode());
//        episodeResponse.setNumberEpisode(episode.getNameEpisode());
//        episodeResponse.setDuration(episode.getDuration());
//        episodeResponse.setAudioUrl(episode.getAudio_url());
//        episodeResponse.setCoverEpisode(episode.getCoverEpisode());
//        episodeResponse.setPodcastSeriesId(episode.getPodcastSeries() != null ? episode.getCoverEpisode() : null);
//        episodeResponse.setId(episode.getId());
//
//        return episodeResponse;
//    }

    EpisodeResponse toEpisodeResponse(Episode episode);
    Episode toEpisode(EpisodeRequest request);
    List<EpisodeResponse> toListEpisodes(List<Episode> episodeList);
}
