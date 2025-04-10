package identity.TuanHuy.service;
import identity.TuanHuy.dto.request.PodcastSeriesRequest;
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

//    public PodcastSeriesResponse createPodcastSeries(PodcastSeriesRequest request){
//                if(podcastSeriesRepository.existsByTitle(request.getTitle())){
//                    throw new AppException(ErrorCode.PODCAST_NAME_EXISTS);
//                }
//
//        PodcastSeries podcastSeries = new PodcastSeries();
//        podcastSeries.setAuthor(request.getAuthor());
//        podcastSeries.setCoverUrl(request.getCoverUrl());
//        podcastSeries.setNarrator(request.getNarrator());
//        podcastSeries.setTitle(request.getTitle());
//        podcastSeries.setTotalDuration(request.getTotalDuration());
//
//        podcastSeriesRepository.save(podcastSeries);
//
//        return podcastSeriesMapper.toPodcastSeriesResponse(podcastSeries);
//    }

    public List<PodcastSeriesResponse> getAllPodcastSeries(){
        List<PodcastSeries> podcastSeriesList = new ArrayList<>(podcastSeriesRepository.findAll());
        return podcastSeriesMapper.toPodcastsSeries(podcastSeriesList);
    }

    public PodcastSeriesResponse createPodcastSeries(PodcastSeriesRequest podcastSeriesRequest, MultipartFile coverImage){
            if(podcastSeriesRepository.existsByTitle(podcastSeriesRequest.getTitle())){
                throw new AppException(ErrorCode.PODCAST_TITLE_ALREADY_EXITS);
            }
            String urlCover = null;
            if(!coverImage.isEmpty() && (coverImage != null) ){
                    urlCover = fileUploadService.uploadImage(coverImage);
            }

            PodcastSeries podcastSeries = new PodcastSeries();
            podcastSeries.setCreatedAt(LocalDateTime.now());
            podcastSeries.setAuthor(podcastSeriesRequest.getAuthor());
            podcastSeries.setCoverUrl(urlCover);
            podcastSeries.setTitle(podcastSeries.getTitle());
            podcastSeries.setNarrator(podcastSeries.getNarrator());
            podcastSeries.setCreateUpdate(LocalDateTime.now());
            podcastSeries.setTotalDuration(null);

            podcastSeriesRepository.save(podcastSeries);

            return podcastSeriesMapper.toPodcastSeriesResponse(podcastSeries);

    }



}
