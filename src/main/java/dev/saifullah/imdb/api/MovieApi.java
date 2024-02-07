package dev.saifullah.imdb.api;

import dev.saifullah.imdb.model.Movie;
import dev.saifullah.imdb.utility.PageResponse;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Validated
@RequestMapping(value = "/api/v1")
public interface MovieApi {


    @Operation(description = "The list is ordered by movie name, ascending.",summary = "Retrieve a list of movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "429", description = "Too many requests. Rate limit exceeded.") })
    @RequestMapping(value = "/movies",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @RateLimiter(name = "rateLimiter", fallbackMethod = "limitFallback")
    ResponseEntity<PageResponse> moviesGet(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "movieName", defaultValue = "", required = true) String movieName
    );

    @Operation(summary = "Retrieve a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "429", description = "Too many requests. Rate limit exceeded.") })
    @RequestMapping(value = "/movies/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @RateLimiter(name = "rateLimiter",fallbackMethod = "limitFallback")
    ResponseEntity<Optional<Movie>> moviesIdGet(@PathVariable ObjectId id);

}
