package user;

/**
 * The type Notification.
 */
public class Notification {
    private String movieName;
    private String message;

    /**
     * Instantiates a new Notification.
     */
    public Notification() {
    }

    /**
     * Instantiates a new Notification.
     *
     * @param movieName the movie name
     * @param message   the message
     */
    public Notification(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    /**
     * Gets movie name.
     *
     * @return the movie name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Sets movie name.
     *
     * @param movieName the movie name
     */
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
