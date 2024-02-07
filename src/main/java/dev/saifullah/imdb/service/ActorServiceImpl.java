package dev.saifullah.imdb.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Aggregates;
import dev.saifullah.imdb.model.Actor;
import dev.saifullah.imdb.repository.ActorRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<Actor> singleActor(ObjectId id) {
        Optional<Actor> actorInDb = actorRepository.findById(id);

        Actor actorResponse = Actor.builder()
                .primaryName(actorInDb.get().getPrimaryName())
                .id(actorInDb.get().getId())
                .build();

        return Optional.ofNullable(actorResponse);
    }

//This is incomplete implementation
    public Optional<Document>  performLookup(ObjectId actorId) {
        //65b6e760407a9b5192ef94fa
        // lookup needed in relations as well
        AggregationOperation match = Aggregation.match(Criteria.where("id").is(actorId));

        AddFieldsOperation addFieldsOperation = Aggregation.addFields().addField("NewTitleId")
                .withValue( new Document("$split", Arrays.asList("$knownForTitles", ","))).build();

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("titles")
                .localField("NewTitleId")
                .foreignField("tconst")
                .as("MovieInfo");


        Aggregation agg =newAggregation(
                match,
                addFieldsOperation,
                unwind("$NewTitleId"),
                lookupOperation
        );


        Document actor =mongoTemplate.aggregate(agg,"actors",Document.class).getRawResults();

        return Optional.of(actor);
    }

}
