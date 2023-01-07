package movie;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Movie.
 */
public final class Movie {
    private String name;
    private String year;
    private int duration;
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> actors = new ArrayList<>();
    private ArrayList<String> countriesBanned = new ArrayList<>();
    private int numLikes;
    private double rating;
    private int numRatings;
    @JsonIgnore
    private ArrayList<Integer> ratings = new ArrayList<>();

    /**
     * Instantiates a new Movie.
     */
    public Movie() {
    }

    /**
     * Instantiates a new Movie.
     *
     * @param movie the movie
     */
    public Movie(final Movie movie) {
        this.name = movie.name;
        this.rating = movie.rating;
        this.numRatings = movie.numRatings;
        this.year = movie.year;
        this.numLikes = movie.numLikes;
        this.duration = movie.duration;

        this.ratings = movie.ratings;

        this.genres.addAll(movie.genres);
        this.actors.addAll(movie.actors);
        this.countriesBanned.addAll(movie.countriesBanned);
    }

    /**
     * Instantiates a new Movie.
     *
     * @param movieName the movie name
     */
    public Movie(final String movieName) {
        this.name = movieName;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(final String year) {
        this.year = year;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Gets genres.
     *
     * @return the genres
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Sets genres.
     *
     * @param genres the genres
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Gets actors.
     *
     * @return the actors
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * Sets actors.
     *
     * @param actors the actors
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * Gets countries banned.
     *
     * @return the countries banned
     */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     * Sets countries banned.
     *
     * @param countriesBanned the countries banned
     */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    /**
     * Gets ratings.
     *
     * @return the ratings
     */
    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    /**
     * Gets num likes.
     *
     * @return the num likes
     */
    public int getNumLikes() {
        return numLikes;
    }

    /**
     * Sets num likes.
     *
     * @param numLikes the num likes
     */
    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(final double rating) {
//        BigDecimal bd = BigDecimal.valueOf(amount).setScale(2, RoundingMode.FLOOR);
//        this.rating = bd.doubleValue();
        this.rating = rating;
    }

    /**
     * Gets num ratings.
     *
     * @return the num ratings
     */
    public int getNumRatings() {
        return numRatings;
    }

    /**
     * Sets num ratings.
     *
     * @param numRatings the num ratings
     */
    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    /**
     * Overrides equals(Object o) method.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return name.equals(movie.name);
    }

    /**
     * Overrides hashCode() method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Increment number of likes.
     */
    public void incrementNumberOfLikes() {
        numLikes++;
    }
}
