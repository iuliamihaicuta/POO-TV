package action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.Output;
import movie.Movie;
import user.Ratings;
import user.User;

import java.util.ArrayList;

import static constansts.Constants.*;

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
//            output.addPOJO(new Output(user, movie));
        }
        output.addPOJO(new Output(user, movie));
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
                          final ArrayList<User> users,
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
//        replaceMovie(user, movie);
        for (User user1 : users)
            replaceMovie(user1, movie);
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
                          final ArrayList<User> users,
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

//        if (user.getRatedMovies().contains(movie)) {
//            output.addPOJO(new Output());
//            return;
//        }

//        user.getRatedMovies().remove(movie);

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
        for (User user1 : users)
            replaceMovie(user1, movie);

//        user.getRatedMovies().add(new Movie(movie));
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

    private static double getAverage(final ArrayList<Integer> list) {
        return list.stream()
                .mapToInt(a -> a)
                .average().orElse(0);
    }
}
