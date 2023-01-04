package user;

import java.util.Objects;

public class Ratings {
    private String movieName;
    private int rating;

    public Ratings(final String movieName, final int rating) {
        this.movieName = movieName;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getRating() {
        return rating;
    }

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
