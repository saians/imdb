package dev.saifullah.imdb.service;

import dev.saifullah.imdb.utility.PageResponse;
import dev.saifullah.imdb.model.Movie;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface MovieService {
    PageResponse allTitles(int pageNo, int pageSize, String movieName);
    Optional<Movie> singleTitle(ObjectId id);
}
