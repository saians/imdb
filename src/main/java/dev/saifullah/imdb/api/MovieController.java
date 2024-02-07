package dev.saifullah.imdb.api;


import dev.saifullah.imdb.utility.PageResponse;
import dev.saifullah.imdb.model.Movie;
import dev.saifullah.imdb.service.MovieServiceImpl;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MovieController implements MovieApi {

    @Autowired
    private MovieServiceImpl movieService;

    @Override
    @GetMapping("/movies")
    @RateLimiter(name = "rateLimiter",fallbackMethod = "limitFallback")
    public ResponseEntity<PageResponse> moviesGet(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "movieTitle", defaultValue = "", required = true) String movieTitle
    ) {
        return new ResponseEntity<>(movieService.allTitles(pageNo,pageSize,movieTitle), HttpStatus.OK);
    }

    @Override
    @GetMapping("/movies/{id}")
    @RateLimiter(name = "rateLimiter",fallbackMethod = "limitFallback")
    public ResponseEntity<Optional<Movie>> moviesIdGet(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Movie>>(movieService.singleTitle(id), HttpStatus.OK);
    }

    public ResponseEntity<String> limitFallback(Exception ex) {
        System.out.println("-------------------   Request Limit Exceeded!  ----------------------------");
        return new ResponseEntity<String>("Request Limit Exceeded!", HttpStatus.TOO_MANY_REQUESTS);
    }

}
