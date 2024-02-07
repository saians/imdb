package dev.saifullah.imdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "titles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    private ObjectId id;

    private String tconst;

    private String titleType;

    private String primaryTitle;

    private String originalTitle;

    private String isAdult;

    private String startYear;

    private String endYear;

    private String runtimeMinutes;

    private String genres;
}
