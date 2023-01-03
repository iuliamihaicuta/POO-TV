package pages;

import movie.MovieList;

import java.util.ArrayList;

/**
 * The type Movies page.
 */
public final class MoviesPage extends Page {
    private static MoviesPage instance = null;
    private MovieList movies;
    private MoviesPage() {
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

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static MoviesPage getInstance() {
        if (instance == null) {
            instance = new MoviesPage();
        }

        return instance;
    }
}
