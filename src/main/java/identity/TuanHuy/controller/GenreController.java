package identity.TuanHuy.controller;

import identity.TuanHuy.dto.reponse.ApiResponse;
import identity.TuanHuy.dto.request.GenreCreateRequest;
import identity.TuanHuy.entity.Genre;
import identity.TuanHuy.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genres")
@CrossOrigin(origins = "*") // Allow requests from the specified domain
public class GenreController {

    @Autowired
    private GenreService genreService;

    ApiResponse<Genre> apiResponse;

//-----------------------------------------------------------
    // Lấy danh sách tất cả genres
    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    // Lấy genre theo id
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        Optional<Genre> genre = genreService.getGenreById(id);
        return genre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Thêm mới genre
    @PostMapping
    public ApiResponse<Genre> addGenre(@RequestBody GenreCreateRequest request) {
        Genre genre = genreService.addGenre(request);
        apiResponse = ApiResponse.<Genre>builder()
                .code(200)
                .message("oke luôn")
                .result(genre).build();
        return apiResponse;
    }

    // Cập nhật genre
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genreDetails) {
        try {
            return ResponseEntity.ok(genreService.updateGenre(id, genreDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa genre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        try {
            genreService.deleteGenre(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
