package identity.TuanHuy.service;
import identity.TuanHuy.dto.request.EpisodeCreateFormRequest;
import identity.TuanHuy.dto.request.EpisodeRequest;
import identity.TuanHuy.dto.response.EpisodeResponse;
import identity.TuanHuy.entity.Episode;
import identity.TuanHuy.entity.PodcastSeries;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.mapper.EpisodeMapper;
import identity.TuanHuy.repository.EpisodeRepository;
import identity.TuanHuy.repository.PodcastSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final PodcastSeriesRepository podcastSeriesRepository;
    private final EpisodeMapper episodeMapper;
    private final FileUploadService fileUploadService;

    public EpisodeResponse createEpisode(EpisodeCreateFormRequest episodeCreateFormRequest){
         PodcastSeries podcastSeries = podcastSeriesRepository.findById(episodeCreateFormRequest.getPodcastSeriesId())
                .orElseThrow(() -> new AppException(ErrorCode.PODCAST_NOT_EXISTS));

        episodeRepository.findByNameEpisode(episodeCreateFormRequest.getNameEpisode())
                .ifPresent(episode -> {
                    throw new AppException(ErrorCode.THIS_EPISODE_ALREADY_EXIST);
                });

        String url_video_online_episode = fileUploadService.uploadEpisode(episodeCreateFormRequest.getAudioUrl() , podcastSeries.getTitle());
        String url_cover_online_episode = fileUploadService.uploadEpisode(episodeCreateFormRequest.getCoverChapter(),podcastSeries.getTitle());

        Episode episode = new Episode();
        episode.setAudio_url(url_video_online_episode);
        episode.setCoverEpisode(url_cover_online_episode);
        episode.setNameEpisode(episodeCreateFormRequest.getNameEpisode());
        episode.setCreateAt(LocalDateTime.now());
        episode.setUpdateAt(LocalDateTime.now());
        episode.setPodcastSeries(podcastSeries);
        episode.setDuration(episodeCreateFormRequest.getDuration());

        episodeRepository.save(episode);

        return episodeMapper.toEpisodeResponse(episode);

    }


    public List<EpisodeResponse> getAllEpisodes(){
        if(episodeRepository.findAll() != null){
        return episodeMapper.toListEpisodes(episodeRepository.findAll());
        }
        throw new AppException(ErrorCode.EPISODE_IS_NULL);
    }


}
