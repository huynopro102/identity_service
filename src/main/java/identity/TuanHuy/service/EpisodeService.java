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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

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

        String url_video_online_episode =
                fileUploadService.uploadAudioEpisode(
                        episodeCreateFormRequest.getAudioUrl() , podcastSeries.getTitle() , episodeCreateFormRequest.getNumberEpisode()
                );

        String url_cover_online_episode =
                fileUploadService.uploadCoverImageEpisode(
                        episodeCreateFormRequest.getCoverChapter(),podcastSeries.getTitle(),episodeCreateFormRequest.getNumberEpisode()
                );

        Episode episode = new Episode();
        episode.setAudio_url(url_video_online_episode);
        episode.setCoverEpisode(url_cover_online_episode);
        episode.setCreateAt(LocalDateTime.now());
        episode.setUpdateAt(LocalDateTime.now());
        episode.setNumberEpisode(episodeCreateFormRequest.getNumberEpisode());
        episode.setNameEpisode(episodeCreateFormRequest.getNameEpisode());
        episode.setPodcastSeries(podcastSeries);
        episode.setDuration(episodeCreateFormRequest.getDuration());

        if(podcastSeries.getEpisodes() == null){
                podcastSeries.setEpisodes(new HashSet<>());
        }
        boolean exits_episode_number = podcastSeries.getEpisodes().stream()
                        .anyMatch(episode1 -> Objects.equals(episode1.getNumberEpisode(),episodeCreateFormRequest.getNumberEpisode()));


        if (exits_episode_number){
            throw new AppException(ErrorCode.THIS_EPISODE_ALREADY_EXIST);
        }

        podcastSeries.getEpisodes().add(episode);
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
