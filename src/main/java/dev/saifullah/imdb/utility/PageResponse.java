package dev.saifullah.imdb.utility;

import dev.saifullah.imdb.model.Movie;
import lombok.Data;

import java.util.List;

@Data
public class PageResponse {

    private List<Movie> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
