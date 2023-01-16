package currentPosition;

import movie.Movie;
import pages.Page;
import pages.types.UnauthHomepage;
import user.User;

/**
 * The type Current position.
 */
public final class CurrentPosition {
    private User currentUser;
    private Page currentPage;
    private Movie currentMovie;
    private int currentRating;
    private static CurrentPosition instance = null;

    /**
     * Instantiates a new Current position.
     */
    private CurrentPosition() {
        currentPage = new UnauthHomepage();
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

    /**
     * Gets current page.
     *
     * @return the current page
     */
    public Page getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets current page.
     *
     * @param currentPage the current page
     */
    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Gets current movie.
     *
     * @return the current movie
     */
    public Movie getCurrentMovie() {
        return currentMovie;
    }

    /**
     * Sets current movie.
     *
     * @param currentMovie the current movie
     */
    public void setCurrentMovie(final Movie currentMovie) {
        this.currentMovie = currentMovie;
    }

    /**
     * Gets current rating.
     *
     * @return the current rating
     */
    public int getCurrentRating() {
        return currentRating;
    }

    /**
     * Sets current rating.
     *
     * @param currentRating the current rating
     */
    public void setCurrentRating(final int currentRating) {
        this.currentRating = currentRating;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CurrentPosition getInstance() {
        if (instance == null) {
            instance = new CurrentPosition();
        }

        return instance;
    }

    /**
     * Sets instance.
     *
     * @param currentPosition the current position
     */
    public static void setInstance(final CurrentPosition currentPosition) {
        instance = currentPosition;
    }
}
