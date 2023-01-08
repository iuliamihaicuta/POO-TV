package user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import movie.Movie;
import user.attributes.Credentials;
import user.attributes.Notification;
import user.attributes.Ratings;

import java.util.ArrayList;
import java.util.Objects;

import static constants.Constants.NUMBER_FREE_MOVIES;

/**
 * The type User.
 */
public class User {
    private Credentials credentials;
    private int tokensCount = 0;
    private int numFreePremiumMovies = NUMBER_FREE_MOVIES;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();
    private ArrayList<Notification> notifications = new ArrayList<>();
    @JsonIgnore
    private ArrayList<String> subscribedGenres = new ArrayList<>();
    @JsonIgnore
    private ArrayList<Ratings> ratings = new ArrayList<>();

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param user the user
     */
    public User(final User user) {
        this.credentials = new Credentials();
        this.credentials.setName(user.getCredentials().getName());
        this.credentials.setAccountType(user.getCredentials().getAccountType());
        this.credentials.setBalance(user.getCredentials().getBalance());
        this.credentials.setCountry(user.getCredentials().getCountry());
        this.credentials.setPassword(user.getCredentials().getPassword());

        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.numFreePremiumMovies;

        this.purchasedMovies = new ArrayList<>();
        this.purchasedMovies.addAll(user.purchasedMovies);
        this.watchedMovies = new ArrayList<>();
        this.watchedMovies.addAll(user.watchedMovies);
        this.likedMovies = new ArrayList<>();
        this.likedMovies.addAll(user.likedMovies);
        this.ratedMovies = new ArrayList<>();
        this.ratedMovies.addAll(user.ratedMovies);
        this.notifications = new ArrayList<>();
        this.notifications.addAll(user.notifications);
        this.subscribedGenres = new ArrayList<>();
        this.subscribedGenres.addAll(user.subscribedGenres);
        this.ratings = new ArrayList<>();
        this.ratings.addAll(user.ratings);
    }

    /**
     * Instantiates a new User.
     *
     * @param credentials the credentials
     */
    public User(final Credentials credentials) {
        this.credentials = new Credentials();
        this.credentials.setName(credentials.getName());
        this.credentials.setAccountType(credentials.getAccountType());
        this.credentials.setBalance(credentials.getBalance());
        this.credentials.setCountry(credentials.getCountry());
        this.credentials.setPassword(credentials.getPassword());
    }

    /**
     * Gets credentials.
     *
     * @return the credentials
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Sets credentials.
     *
     * @param credentials the credentials
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Gets tokens count.
     *
     * @return the tokens count
     */
    public int getTokensCount() {
        return tokensCount;
    }

    /**
     * Sets tokens count.
     *
     * @param tokensCount the tokens count
     */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    /**
     * Gets num free premium movies.
     *
     * @return the num free premium movies
     */
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    /**
     * Sets num free premium movies.
     *
     * @param numFreePremiumMovies the num free premium movies
     */
    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    /**
     * Gets purchased movies.
     *
     * @return the purchased movies
     */
    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    /**
     * Sets purchased movies.
     *
     * @param purchasedMovies the purchased movies
     */
    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    /**
     * Gets watched movies.
     *
     * @return the watched movies
     */
    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    /**
     * Sets watched movies.
     *
     * @param watchedMovies the watched movies
     */
    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    /**
     * Gets liked movies.
     *
     * @return the liked movies
     */
    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    /**
     * Sets liked movies.
     *
     * @param likedMovies the liked movies
     */
    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    /**
     * Gets rated movies.
     *
     * @return the rated movies
     */
    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    /**
     * Sets rated movies.
     *
     * @param ratedMovies the rated movies
     */
    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    /**
     * Gets notifications.
     *
     * @return the notifications
     */
    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    /**
     * Sets notifications.
     *
     * @param notifications the notifications
     */
    public void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     * Gets subscribed genres.
     *
     * @return the subscribed genres
     */
    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    /**
     * Sets subscribed genres.
     *
     * @param subscribedGenres the subscribed genres
     */
    public void setSubscribedGenres(final ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    /**
     * Gets ratings.
     *
     * @return the ratings
     */
    public ArrayList<Ratings> getRatings() {
        return ratings;
    }

    /**
     * Sets ratings.
     *
     * @param ratings the ratings
     */
    public void setRatings(final ArrayList<Ratings> ratings) {
        this.ratings = ratings;
    }

    /**
     * Decrement number of free movies.
     */
    public void decrementNumberOfFreeMovies() {
        numFreePremiumMovies--;
    }

    /**
     * Increment number of free movies.
     */
    public void incrementNumberOfFreeMovies() {
        numFreePremiumMovies++;
    }

    /**
     * Decrement tokens count.
     *
     * @param tokens the tokens
     */
    public void decrementTokensCount(final int tokens) {
        tokensCount -= tokens;
    }

    /**
     * Increment tokens count.
     *
     * @param tokens the tokens
     */
    public void incrementTokensCount(final int tokens) {
        tokensCount += tokens;
    }

    /**
     * Remove movie boolean.
     *
     * @param movie the movie
     * @return the boolean
     */
    public boolean removeMovie(final Movie movie) {
        likedMovies.remove(movie);
        watchedMovies.remove(movie);
        ratedMovies.remove(movie);
        return purchasedMovies.remove(movie);
    }

    /**
     * Equals method.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return credentials.equals(user.credentials);
    }

    /**
     * HashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(credentials);
    }

    /**
     * Refund.
     */
    public void refund() {
    }

    /**
     * Gets recommendation.
     *
     * @param output the output
     */
    public void getRecommendation(final ArrayNode output) {
    }
}
