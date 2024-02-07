package dev.saifullah.imdb.api;

import dev.saifullah.imdb.model.Actor;
import dev.saifullah.imdb.model.Movie;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Validated
@RequestMapping(value = "/api/v1")
public interface ActorApi {

    @Operation(summary = "Retrieve an actor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(schema = @Schema(implementation = Actor.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "429", description = "Too many requests. Rate limit exceeded.") })
    @RequestMapping(value = "/actors/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
//    @RateLimiter(name = "rateLimiter", fallbackMethod = "limitFallback")
    ResponseEntity<Optional<Actor>> actorIdGet(@PathVariable ObjectId id);


    @Operation(summary = "Retrieve an actor appearances")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(schema = @Schema(implementation = Document.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "429", description = "Too many requests. Rate limit exceeded.") })
    @RequestMapping(value = "/actors/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @RateLimiter(name = "rateLimiter", fallbackMethod = "limitFallback")
    ResponseEntity<Optional<Document>> actorIdGetAppearance(@PathVariable ObjectId id);
}
