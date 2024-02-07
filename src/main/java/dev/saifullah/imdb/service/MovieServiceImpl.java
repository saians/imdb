package dev.saifullah.imdb.service;

import dev.saifullah.imdb.model.Actor;
import dev.saifullah.imdb.utility.PageResponse;
import dev.saifullah.imdb.repository.MovieRepository;
import dev.saifullah.imdb.model.Movie;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    public PageResponse allTitles(int pageNo, int pageSize, String movieTitle){
        Sort sort =Sort.by("primaryTitle").ascending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        Page<Movie> titles = movieRepository.findByPrimaryTitleLike(pageable, movieTitle);
        List<Movie> content = titles.getContent();

        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(content);
        pageResponse.setPageNo(titles.getNumber());
        pageResponse.setPageSize(titles.getSize());
        pageResponse.setTotalElements(titles.getTotalElements());
        pageResponse.setTotalPages(titles.getTotalPages());
        pageResponse.setLast(titles.isLast());

        return pageResponse;
    }

    public Optional<Movie> singleTitle(ObjectId id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()){
            return Optional.empty();
        }
        Movie actorResponse = Movie.builder()
                .id(movie.get().getId())
                .primaryTitle(movie.get().getPrimaryTitle())
                .startYear(movie.get().getStartYear())
                .build();

        return Optional.ofNullable(actorResponse);
    }
}
