package movie;

import action.filter.Contains;
import action.filter.Sort;
import pages.types.MoviesPage;

import java.util.ArrayList;
import java.util.Comparator;

import static constansts.Constants.DECREASING;
import static constansts.Constants.INCREASING;

/**
 * The type Movie list.
 */
public final class MovieList {
    private ArrayList<Movie> movies = new ArrayList<>();

    /**
     * Instantiates a new Movie list.
     */
    public MovieList() {
    }

    /**
     * Instantiates a new Movie list.
     *
     * @param movieList the movie list
     */
    public MovieList(final MovieList movieList) {
        this.movies.addAll(movieList.movies);
    }

    /**
     * Instantiates a new Movie list.
     *
     * @param movies the movies
     */
    public MovieList(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Gets movies.
     *
     * @return the movies
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * Sets movies.
     *
     * @param movies the movies
     */
    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    private void sortMoviesByRatings(final int mode) {
        if (mode == DECREASING) {
            movies.sort((o1, o2) -> (int) (o2.getRating() - o1.getRating()));
            return;
        }

        movies.sort(Comparator.comparingDouble(Movie::getRating));
    }

    private void sortMoviesByDuration(final int mode) {
        if (mode == DECREASING) {
            movies.sort((o1, o2) -> o2.getDuration() - o1.getDuration());
            return;
        }

        movies.sort(Comparator.comparingInt(Movie::getDuration));
    }

    /**
     * Sort movies.
     *
     * @param sort the sort
     */
    public void sortMovies(final Sort sort) {
        if (sort.getRating() != null) {
            switch (sort.getRating()) {
                case "decreasing" -> sortMoviesByRatings(DECREASING);
                case "increasing" -> sortMoviesByRatings(INCREASING);
                default -> throw new IllegalArgumentException("Unrecognized action");
            }
        }

        if (sort.getDuration() != null) {
            switch (sort.getDuration()) {
                case "decreasing" -> sortMoviesByDuration(DECREASING);
                case "increasing" -> sortMoviesByDuration(INCREASING);
                default -> throw new IllegalArgumentException("Unrecognized action");
            }
        }
    }

    private void containsActors(final ArrayList<String> actors) {
        MovieList newMovieList = new MovieList();

        for (Movie movie : MoviesPage.getInstance().getMovies().getMovies()) {
            for (String actor : actors) {
                if (!movie.getActors().contains(actor)) {
                    break;
                }

                newMovieList.getMovies().add(new Movie(movie));
            }
        }
        MoviesPage.getInstance().setMovies(newMovieList);

    }

    private void containsGenres(final ArrayList<String> genres) {
        MovieList newMovieList = new MovieList();

        for (Movie movie : MoviesPage.getInstance().getMovies().getMovies()) {
            for (String genre : genres) {
                if (!movie.getGenres().contains(genre)) {
                    break;
                }

                newMovieList.getMovies().add(new Movie(movie));
            }
        }
        MoviesPage.getInstance().setMovies(newMovieList);
    }

    /**
     * Gets movies with certain actors or genres
     *
     * @param contains the contains
     */
    public void moviesContain(final Contains contains) {
        if (contains.getActors().size() > 0) {
            containsActors(contains.getActors());
        }

        if (contains.getGenre().size() > 0) {
            containsGenres(contains.getGenre());
        }
    }

    /**
     * Gets permitted movies.
     *
     * @param country the country
     * @return the permitted movies
     */
    public MovieList getPermittedMovies(final String country) {
        MovieList permittedMovies = new MovieList();

        for (Movie movie : movies) {
            if (!movie.getCountriesBanned().contains(country)) {
                permittedMovies.getMovies().add(movie);
            }
        }

        return permittedMovies;
    }

    /**
     * Gets movie by name.
     *
     * @param name the name
     * @return the movie by name
     */
    public Movie getMovieByName(final String name) {
        for (Movie movie : movies) {
            if (movie.getName().equals(name)) {
                return movie;
            }
        }

        return null;
    }
}
