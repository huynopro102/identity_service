package identity.TuanHuy.service;
import identity.TuanHuy.dto.request.PodcastSeriesFormRequest;
import identity.TuanHuy.dto.response.PodcastSeriesResponse;
import identity.TuanHuy.entity.PodcastSeries;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.mapper.PodcastSeriesMapper;
import identity.TuanHuy.repository.PodcastSeriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@AllArgsConstructor
@Service
public class PodcastSeriesService {

    private final PodcastSeriesRepository podcastSeriesRepository;
    private final FileUploadService fileUploadService;
    private final PodcastSeriesMapper podcastSeriesMapper;

    public List<PodcastSeriesResponse> getAllPodcastSeries(){
        List<PodcastSeries> podcastSeriesList = new ArrayList<>(podcastSeriesRepository.findAll());
        return podcastSeriesMapper.toPodcastsSeries(podcastSeriesList);
    }

    public PodcastSeriesResponse createPodcastSeries(PodcastSeriesFormRequest podcastSeriesFormRequest){
            if(podcastSeriesRepository.existsByTitle(podcastSeriesFormRequest.getTitle())){
                throw new AppException(ErrorCode.PODCAST_TITLE_ALREADY_EXITS);
            }
            String urlCover = null;
            MultipartFile coverImage = podcastSeriesFormRequest.getCoverImage();
            if(!coverImage.isEmpty() && (coverImage != null) ){
                    urlCover = fileUploadService.uploadCoverImagePodcast(coverImage,podcastSeriesFormRequest.getTitle());
            }

            PodcastSeries podcastSeries = new PodcastSeries();

            podcastSeries.setCreatedAt(LocalDateTime.now());
            podcastSeries.setCreateUpdate(LocalDateTime.now());

            podcastSeries.setAuthor(podcastSeriesFormRequest.getAuthor());
            podcastSeries.setCoverUrl(urlCover);
            podcastSeries.setTitle(podcastSeriesFormRequest.getTitle());
            podcastSeries.setNarrator(podcastSeriesFormRequest.getNarrator());
            podcastSeries.setTotalDuration(podcastSeriesFormRequest.getTotalDuration());

            podcastSeriesRepository.save(podcastSeries);

            return podcastSeriesMapper.toPodcastSeriesResponse(podcastSeries);

    }



}
