package be.bobberttech.movielistr.movies.service;

import be.bobberttech.movielistr.movies.domain.Movie;
import com.google.common.collect.Lists;
import com.google.inject.Singleton;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static be.bobberttech.movielistr.movies.domain.Director.newDirector;
import static be.bobberttech.movielistr.movies.domain.Movie.newMovie;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Singleton
public class MovieService {

    @Inject
    public MovieService() {
        setDummyMovies();
    }

    private Map<String, Movie> movies = new HashMap<>();

    public List<Movie> getMovies() {
        return Lists.newArrayList(this.movies.values());
    }

    public void saveOrUpdateMovie(Movie movie) {
        if (isEmpty(movie.getId())) {
            movie.setId(generateId());
        }
        this.movies.put(movie.getId(), movie);
    }

    public void deleteMovie(String id) {
        this.movies.remove(id);
    }

    public Movie getMovie(String id) {
        return this.movies.get(id);
    }

    private String generateId() {
        String result = randomAlphanumeric(10);
        while (movies.get(result) != null) {
            result = randomAlphanumeric(10);
        }
        return result;
    }

    private void setDummyMovies() {
        saveOrUpdateMovie(newMovie()
                .withDirector(newDirector()
                        .withFirstName("James")
                        .withLastName("Cameron")
                        .build())
                .withDuration(162)
                .withTitle("Avatar")
                .withRating(6)
                .withYear(2009)
                .withSeen(true)
                .build());
        saveOrUpdateMovie(newMovie()
                .withDirector(newDirector()
                        .withFirstName("Peter")
                        .withLastName("Jackson")
                        .build())
                .withDuration(178)
                .withTitle("Lord of the Rings: Fellowship of the ring")
                .withRating(9)
                .withYear(2001)
                .withSeen(true)
                .build());
        saveOrUpdateMovie(newMovie()
                .withDirector(newDirector()
                        .withFirstName("Quentin")
                        .withLastName("Tarantino")
                        .build())
                .withDuration(154)
                .withTitle("Pulp Fiction")
                .withRating(9)
                .withYear(1994)
                .withSeen(true)
                .build());
        saveOrUpdateMovie(newMovie()
                .withDirector(newDirector()
                        .withFirstName("Tobe")
                        .withLastName("Hooper")
                        .build())
                .withDuration(83)
                .withTitle("The Texas Chainsaw Massacre")
                .withRating(8)
                .withYear(1974)
                .withSeen(false)
                .build());
        saveOrUpdateMovie(newMovie()
                .withDirector(newDirector()
                        .withFirstName("Guillermo")
                        .withLastName("Del Toro")
                        .build())
                .withDuration(131)
                .withTitle("Pacific Rim")
                .withRating(7)
                .withYear(2013)
                .withSeen(false)
                .build());
    }

}
