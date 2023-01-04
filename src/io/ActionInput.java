package io;

import action.filter.Filters;
import movie.Movie;
import user.Credentials;

/**
 * The type Action input.
 */
public class ActionInput {
    private String type;
    private String page;
    private String movie;
    private String feature;
    private String subscribedGenre;
    private Credentials credentials;
    private String startsWith;
    private Filters filters;
    private int count;
    private int rate;
    private Movie addedMovie;
    private String deletedMovie;
    /**
     * Instantiates a new Action input.
     */
    public ActionInput() {
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(final String page) {
        this.page = page;
    }

    /**
     * Gets movie.
     *
     * @return the movie
     */
    public String getMovie() {
        return movie;
    }

    /**
     * Sets movie.
     *
     * @param movie the movie
     */
    public void setMovie(final String movie) {
        this.movie = movie;
    }

    /**
     * Gets feature.
     *
     * @return the feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * Sets feature.
     *
     * @param feature the feature
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
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
     * Gets starts with.
     *
     * @return the starts with
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * Sets starts with.
     *
     * @param startsWith the starts with
     */
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    /**
     * Gets filters.
     *
     * @return the filters
     */
    public Filters getFilters() {
        return filters;
    }

    /**
     * Sets filters.
     *
     * @param filters the filters
     */
    public void setFilters(final Filters filters) {
        this.filters = filters;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * Gets rate.
     *
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(final int rate) {
        this.rate = rate;
    }

    public Movie getAddedMovie() {
        return addedMovie;
    }

    public void setAddedMovie(final Movie addedMovie) {
        this.addedMovie = addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }
}
