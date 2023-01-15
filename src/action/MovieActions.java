package action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import database.Database;
import io.Output;
import movie.Movie;
import user.attributes.Ratings;
import user.User;

import java.util.ArrayList;

import static constants.Constants.MOVIE_PRICE;
import static constants.Constants.MIN_RATING;
import static constants.Constants.MAX_RATING;

/**
 * The type Movie actions.
 */
public final class MovieActions {

    /**
     * Instantiates a new Movie actions.
     */
    public MovieActions() {
    }

    /**
     * Purchase movie.
     *
     * @param output the output
     */
    public void purchaseMovie(final ArrayNode output) {
        User user = CurrentPosition.getInstance().getCurrentUser();
        Movie movie = CurrentPosition.getInstance().getCurrentMovie();
        if (user.getPurchasedMovies().contains(movie)) {
            output.addPOJO(new Output());
            return;
        }

        boolean movieWasPurchased = false;

        if (user.getNumFreePremiumMovies() > 0
                && user.getCredentials().getAccountType().equals("premium")) {
            user.decrementNumberOfFreeMovies();
            movieWasPurchased = true;
        } else if (user.getTokensCount() >= MOVIE_PRICE) {
            user.decrementTokensCount(MOVIE_PRICE);
            movieWasPurchased = true;
        }

        if (movieWasPurchased) {
            user.getPurchasedMovies().add(new Movie(movie));
            output.addPOJO(new Output(user, movie));
            return;
        }

        output.addPOJO(new Output());
    }

    /**
     * Watch movie.
     *
     * @param output the output
     */
    public void watchMovie(final ArrayNode output) {
        User user = CurrentPosition.getInstance().getCurrentUser();
        Movie movie = CurrentPosition.getInstance().getCurrentMovie();

        if (!user.getPurchasedMovies().contains(movie)) {
            output.addPOJO(new Output());
            return;
        }

        if (!user.getWatchedMovies().contains(movie)) {
            user.getWatchedMovies().add(new Movie(movie));
        }
        output.addPOJO(new Output(user, movie));
    }

    /**
     * Like movie.
     *
     * @param output   the output
     */
    public void likeMovie(final ArrayNode output) {
        User user = CurrentPosition.getInstance().getCurrentUser();
        Movie movie = CurrentPosition.getInstance().getCurrentMovie();

        if (!wasMovieWatched()) {
            output.addPOJO(new Output());
            return;
        }

        movie.incrementNumberOfLikes();

        Database.getInstance().getUsers().forEach(o -> replaceMovie(o, movie));
        user.getLikedMovies().add(new Movie(movie));

        output.addPOJO(new Output(user, movie));
    }

    /**
     * Rate movie.
     *
     * @param rating   the rating
     * @param output   the output
     */
    public void rateMovie(final int rating,
                          final ArrayNode output) {
        User user = CurrentPosition.getInstance().getCurrentUser();
        Movie movie = CurrentPosition.getInstance().getCurrentMovie();

        if (!wasMovieWatched()) {
            output.addPOJO(new Output());
            return;
        }

        if (rating > MAX_RATING || rating < MIN_RATING) {
            output.addPOJO(new Output());
            return;
        }

        Ratings newRating = new Ratings(movie.getName(), rating);
        if (user.getRatings().contains(newRating)) {
            int index = user.getRatings().indexOf(newRating);
            int oldRating = user.getRatings().get(index).getRating();

            movie.getRatings().remove((Integer) oldRating);

            user.getRatings().get(index).setRating(rating);
        } else {
            user.getRatings().add(newRating);
        }

        movie.getRatings().add(rating);
        movie.setNumRatings(movie.getRatings().size());
        movie.setRating(getAverage(movie.getRatings()));

        Database.getInstance().getUsers().forEach(o -> replaceMovie(o, movie));

        if (!user.getRatedMovies().contains(movie)) {
            user.getRatedMovies().add(new Movie(movie));
        }

        output.addPOJO(new Output(user, movie));
    }

    private void replaceMovie(final User user,
                              final Movie movie) {
        int purchaseIdx = user.getPurchasedMovies().indexOf(movie);
        if (purchaseIdx > -1) {
            user.getPurchasedMovies().remove(purchaseIdx);
            user.getPurchasedMovies().add(purchaseIdx, new Movie(movie));
        }

        int watchIdx = user.getWatchedMovies().indexOf(movie);
        if (watchIdx > -1) {
            user.getWatchedMovies().remove(watchIdx);
            user.getWatchedMovies().add(watchIdx, new Movie(movie));
        }

        int likeIdx = user.getLikedMovies().indexOf(movie);
        if (likeIdx > -1) {
            user.getLikedMovies().remove(likeIdx);
            user.getLikedMovies().add(likeIdx, new Movie(movie));
        }

        int rateIdx = user.getRatedMovies().indexOf(movie);
        if (rateIdx > -1) {
            user.getRatedMovies().remove(rateIdx);
            user.getRatedMovies().add(rateIdx, new Movie(movie));
        }
    }

    private boolean wasMovieWatched() {
        User user = CurrentPosition.getInstance().getCurrentUser();
        Movie movie = CurrentPosition.getInstance().getCurrentMovie();

        if (!user.getPurchasedMovies().contains(movie)) {
            return false;
        }

        return user.getWatchedMovies().contains(movie);
    }

    private static double getAverage(final ArrayList<Integer> list) {
        return list.stream()
                .mapToInt(a -> a)
                .average().orElse(0);
    }
}
