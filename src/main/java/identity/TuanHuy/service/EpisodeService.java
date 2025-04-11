package identity.TuanHuy.service;
import identity.TuanHuy.dto.response.EpisodeResponse;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.mapper.EpisodeMapper;
import identity.TuanHuy.repository.EpisodeRepository;
import identity.TuanHuy.repository.PodcastSeriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {
    private EpisodeRepository episodeRepository;
    private PodcastSeriesRepository podcastSeriesRepository;
    private EpisodeMapper episodeMapper;

//    public EpisodeResponse createEpisode(EpisodeRequest request){
//        podcastSeriesRepository.findById(request.getPodcastSeriesId())
//                .orElseThrow(() -> new AppException(ErrorCode.PODCAST_NOT_EXISTS));
//
//        return episodeMapper.toEpisodeResponse(request);
//
//    }
    public List<EpisodeResponse> getAllEpisodes(){
        if(episodeRepository.findAll() != null){
        return episodeMapper.toListEpisodes(episodeRepository.findAll());
        }
        throw new AppException(ErrorCode.EPISODE_IS_NULL);
    }
}
