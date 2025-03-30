package identity.TuanHuy.service;
import identity.TuanHuy.mapper.EpisodeMapper;
import identity.TuanHuy.repository.EpisodeRepository;
import identity.TuanHuy.repository.PodcastSeriesRepository;
import org.springframework.stereotype.Service;

@Service
public class EpisodeService {
    private EpisodeRepository episodeRepository;
    private PodcastSeriesRepository podcastSeriesRepository;
    private EpisodeMapper episodeMapper;
//
//    public EpisodeResponse createEpisode(EpisodeRequest request){
//        podcastSeriesRepository.findById(request.getPodcastSeriesId())
//                .orElseThrow(() -> new AppException(ErrorCode.PODCAST_NOT_EXISTS));
//
//        return episodeMapper.toEpisodeResponse(request);
//
//    }
}
