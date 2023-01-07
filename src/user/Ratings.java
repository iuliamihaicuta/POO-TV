package user;

import java.util.Objects;

/**
 * The type Ratings.
 */
public class Ratings {
    private String movieName;
    private int rating;

    /**
     * Instantiates a new Ratings.
     *
     * @param movieName the movie name
     * @param rating    the rating
     */
    public Ratings(final String movieName, final int rating) {
        this.movieName = movieName;
        this.rating = rating;
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
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ratings ratings = (Ratings) o;
        return movieName.equals(ratings.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName);
    }
}
