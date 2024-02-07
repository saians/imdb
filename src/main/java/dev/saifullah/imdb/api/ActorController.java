package dev.saifullah.imdb.api;

import dev.saifullah.imdb.model.Actor;
import dev.saifullah.imdb.service.ActorServiceImpl;
import dev.saifullah.imdb.service.MovieServiceImpl;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ActorController implements ActorApi {

    @Autowired
    private ActorServiceImpl actorService;

    @Override
    @GetMapping("/actors/{id}")
//    @RateLimiter(name = "rateLimiter",fallbackMethod = "limitFallback")
    public ResponseEntity<Optional<Actor>> actorIdGet(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Actor>>(actorService.singleActor(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/actors/{id}/appearances")
//    @RateLimiter(name = "rateLimiter",fallbackMethod = "limitFallback")
    public ResponseEntity<Optional<Document>> actorIdGetAppearance(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Document>>(actorService.performLookup(id), HttpStatus.OK);
    }

    public ResponseEntity<String> limitFallback(Exception ex) {
        System.out.println("-------------------   Request Limit Exceeded!  ----------------------------");
        return new ResponseEntity<String>("Request Limit Exceeded!", HttpStatus.TOO_MANY_REQUESTS);
    }
}
