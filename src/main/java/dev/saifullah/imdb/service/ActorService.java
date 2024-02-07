package dev.saifullah.imdb.service;

import dev.saifullah.imdb.model.Actor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface ActorService {

    public Optional<Actor> singleActor(ObjectId id);

    public Optional<Document>  performLookup(ObjectId actorId);
}
