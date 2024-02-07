package dev.saifullah.imdb.repository;


import dev.saifullah.imdb.model.Movie;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class MovieRepositoryTest {

    @MockBean
    private MovieRepository repository;

    @Test
    public void MovieRepository_GetById_ReturnsMatchingResults() {


        ObjectId id = new ObjectId("65c14c7e7728326640f777bc");
        Movie movie = Movie.builder().startYear("2019")
                .primaryTitle("Tötet nicht mehr")
                .originalTitle("Tötet nicht mehr")
                .genres("Action").build();

        when(repository.findById(id)).thenReturn(Optional.ofNullable(movie));

        Optional<Movie> movieRes = repository.findById(id);

        assertNotNull(movieRes);
    }

    @Test
    public void MovieRepository_GetByName_ReturnsMatchingResults() {

        Movie movie = Movie.builder().startYear("2019")
                .primaryTitle("Tötet nicht mehr")
                .originalTitle("Tötet nicht mehr")
                .genres("Action").build();
        String testName = "Ball";
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        Page<Movie> pagedResponse = new PageImpl(movies);
        Pageable pageable = PageRequest.of(0, 8);



        when(repository.findByPrimaryTitleLike(pageable,testName)).thenReturn(pagedResponse);

        Page<Movie> movieRes = repository.findByPrimaryTitleLike(pageable,testName);

        assertNotNull(movieRes);
        assertEquals("Tötet nicht mehr",movieRes.stream().findAny().get().getPrimaryTitle());
    }
}
