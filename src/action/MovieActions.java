package action;

import io.Output;
import com.fasterxml.jackson.databind.node.ArrayNode;
import movie.Movie;
import user.User;

import java.util.ArrayList;

import static constansts.Constants.MAX_RATING;
import static constansts.Constants.MIN_RATING;
import static constansts.Constants.MOVIE_PRICE;

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
     * @param movie  the movie
     * @param user   the user
     * @param output the output
     */
    public void purchaseMovie(final Movie movie,
                       final User user,
                       final ArrayNode output) {
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
     * @param movie  the movie
     * @param user   the user
     * @param output the output
     */
    public void watchMovie(final Movie movie,
                           final User user,
                           final ArrayNode output) {
        if (!user.getPurchasedMovies().contains(movie)) {
            output.addPOJO(new Output());
            return;
        }

        if (!user.getWatchedMovies().contains(movie)) {
            user.getWatchedMovies().add(new Movie(movie));
            output.addPOJO(new Output(user, movie));
        }
    }

    /**
     * Like movie.
     *
     * @param movie  the movie
     * @param user   the user
     * @param output the output
     */
    public void likeMovie(final Movie movie,
                          final User user,
                          final ArrayNode output) {
        if (!user.getPurchasedMovies().contains(movie)) {
            output.addPOJO(new Output());
            return;
        }

        if (!user.getWatchedMovies().contains(movie)) {
            output.addPOJO(new Output());
            return;
        }

        movie.incrementNumberOfLikes();
        replaceMovie(user, movie);
        user.getLikedMovies().add(new Movie(movie));

        output.addPOJO(new Output(user, movie));
    }

    /**
     * Rate movie.
     *
     * @param rating the rating
     * @param movie  the movie
     * @param user   the user
     * @param output the output
     */
    public void rateMovie(final int rating,
                          final Movie movie,
                          final User user,
                          final ArrayNode output) {
        if (!user.getPurchasedMovies().contains(movie)) {
            output.addPOJO(new Output());
            return;
        }

        if (!user.getWatchedMovies().contains(movie)) {
            output.addPOJO(new Output());
            return;
        }

        if (rating > MAX_RATING || rating < MIN_RATING) {
            output.addPOJO(new Output());
            return;
        }

        movie.getRatings().add(rating);
        movie.setNumRatings(movie.getRatings().size());
        movie.setRating((int) getAverage(movie.getRatings()));
        replaceMovie(user, movie);
        user.getRatedMovies().add(new Movie(movie));

        output.addPOJO(new Output(user, movie));
    }

    private void replaceMovie(final User user,
                              final Movie movie) {
        int purchaseIdx = user.getPurchasedMovies().indexOf(movie);
        user.getPurchasedMovies().remove(purchaseIdx);
        user.getPurchasedMovies().add(purchaseIdx, new Movie(movie));

        int watchIdx = user.getWatchedMovies().indexOf(movie);
        user.getWatchedMovies().remove(watchIdx);
        user.getWatchedMovies().add(watchIdx, new Movie(movie));

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

    private static double getAverage(final ArrayList<Integer> list) {
        return list.stream()
                .mapToInt(a -> a)
                .average().orElse(0);
    }
}
