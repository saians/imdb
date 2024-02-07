package dev.saifullah.imdb.repository;

import dev.saifullah.imdb.model.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

    public Page<Movie> findByPrimaryTitleLike(Pageable pageable, String primaryTitle);

}
