package user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import movie.Movie;

import java.util.ArrayList;

import static constansts.Constants.NUMBER_FREE_MOVIES;

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

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    public void setSubscribedGenres(ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    /**
     * Decrement number of free movies.
     */
    public void decrementNumberOfFreeMovies() {
        numFreePremiumMovies--;
    }

    /**
     * Decrement tokens count.
     *
     * @param tokens the tokens
     */
    public void decrementTokensCount(final int tokens) {
        tokensCount -= tokens;
    }
}
