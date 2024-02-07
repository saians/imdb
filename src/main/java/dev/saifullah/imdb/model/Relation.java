package dev.saifullah.imdb.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RelatedSorted")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relation {

    @Id
    private ObjectId id;

    private String tconst;

    private Integer ordering;

    private String nconst;

    private String category;

    private String job;

    private String characters;
}
