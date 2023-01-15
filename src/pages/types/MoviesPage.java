package pages.types;

import movie.Movie;
import movie.MovieList;
import pages.Page;

import java.util.ArrayList;

/**
 * The type Movies page.
 */
public final class MoviesPage extends Page {
    private MovieList movies;
    public MoviesPage() {
        this.setName("movies");

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("homepage autentificat");
        this.getAccessiblePages().add("see details");
        this.getAccessiblePages().add("logout");
        this.getAccessiblePages().add("movies");

        this.setActions(new ArrayList<>());
        this.getActions().add("Search");
        this.getActions().add("Filter");
    }

    /**
     * Gets movies.
     *
     * @return the movies
     */
    public MovieList getMovies() {
        return movies;
    }

    /**
     * Sets movies.
     *
     * @param movies the movies
     */
    public void setMovies(final MovieList movies) {
        this.movies = movies;
    }

    @Override
    public void removeMovie(final Movie movie) {
        movies.getMovies().remove(movie);
    }
}
