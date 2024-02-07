package dev.saifullah.imdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "actors")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Actor {

    @Id
    private ObjectId id;

    private String nconst;

    private String primaryName;

    private Integer birthYear;

    private String deathYear;

    private List<String> primaryProfession;

    private List<String> knownForTitles;
}
