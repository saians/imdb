package dev.saifullah.imdb.repository;

import dev.saifullah.imdb.model.Actor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends MongoRepository<Actor, ObjectId> {
}
