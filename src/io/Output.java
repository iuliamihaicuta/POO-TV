package io;

import movie.Movie;
import user.User;

import java.util.ArrayList;

/**
 * The type Output.
 */
public final class Output {
    private String error;
    private ArrayList<Movie> currentMoviesList;
    private User currentUser;

    /**
     * Instantiates a new Output.
     *
     * @param currentUser the current user
     */
    public Output(final User currentUser) {
        this.currentMoviesList = new ArrayList<>();
        this.currentUser = new User(currentUser);
    }

    /**
     * Instantiates a new Output.
     */
    public Output() {
        this.error = "Error";
        this.currentMoviesList = new ArrayList<>();
    }

    /**
     * Instantiates a new Output.
     *
     * @param currentUser the current user
     * @param movies      the movies
     */
    public Output(final User currentUser,
                  final ArrayList<Movie> movies) {
        this.currentUser = new User(currentUser);

        if (movies == null) {
            this.currentMoviesList = null;
            return;
        }

        this.currentMoviesList = new ArrayList<>();
        for (Movie movie: movies) {
            this.currentMoviesList.add(new Movie(movie));
        }
    }

    /**
     * Instantiates a new Output.
     *
     * @param currentUser the current user
     * @param movie       the movie
     */
    public Output(final User currentUser,
                  final Movie movie) {
        this.currentUser = new User(currentUser);

        this.currentMoviesList = new ArrayList<>();
        this.currentMoviesList.add(new Movie(movie));
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(final String error) {
        this.error = error;
    }

    /**
     * Gets current movies list.
     *
     * @return the current movies list
     */
    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    /**
     * Sets current movies list.
     *
     * @param currentMoviesList the current movies list
     */
    public void setCurrentMoviesList(final ArrayList<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    /**
     * Gets current user.
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets current user.
     *
     * @param currentUser the current user
     */
    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }
}
