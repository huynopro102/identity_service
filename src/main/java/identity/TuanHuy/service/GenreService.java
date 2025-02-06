package identity.TuanHuy.service;

import identity.TuanHuy.dto.request.GenreCreateRequest;
import identity.TuanHuy.entity.Genre;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.repository.GenreReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreReponsitory genreRepository;

    // Lấy tất cả genres
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    // Lấy genre theo id
    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    // Thêm mới genre
    public Genre addGenre(GenreCreateRequest request) {
        // Check if genre already exists before creating
        if (genreRepository.findByName(request.getGenrename()).isPresent()) {
            throw new AppException(ErrorCode.GENRE_EXITED);
        }

        Genre genreCreate = new Genre();
        genreCreate.setName(request.getGenrename());
        return genreRepository.save(genreCreate);
    }

    // Cập nhật genre
    public Genre updateGenre(Long id, Genre genreDetails) {
        return genreRepository.findById(id).map(genre -> {
            genre.setName(genreDetails.getName());
            return genreRepository.save(genre);
        }).orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));


    }

    // Xóa genre
    public void deleteGenre(Long id) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
        } else {
            throw new AppException(ErrorCode.GENRE_NOT_FOUND);
        }
    }


}
