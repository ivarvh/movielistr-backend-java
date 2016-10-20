package be.bobberttech.movielistr.movies;

import be.bobberttech.movielistr.movies.domain.Movie;
import be.bobberttech.movielistr.movies.service.MovieService;
import com.google.inject.Singleton;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Singleton
@Path("/movies")
public class MovieResource {

    private final MovieService movieService;

    @Inject
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Movie getMovie(@PathParam("id") String id) {
        return movieService.getMovie(id);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response saveMovie(Movie movie) {
        movieService.saveOrUpdateMovie(movie);
        return Response.created(URI.create("/movies/" + movie.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") String id, Movie movie) {
        if (movie.getId().equals(id)) {
            movieService.saveOrUpdateMovie(movie);
            return Response.noContent().build();
        }
        return Response.status(400).entity("ID of object doesn't match ID in URL.").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") String id) {
        movieService.deleteMovie(id);
        return Response.noContent().build();
    }

}
