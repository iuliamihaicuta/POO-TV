package user;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.Output;
import movie.Movie;
import pages.types.MoviesPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * The type Premium user.
 */
public class PremiumUser extends User {
    /**
     * Instantiates a new Premium user.
     *
     * @param user the user
     */
    public PremiumUser(final User user) {
        super(user);
        this.getCredentials().setAccountType("premium");
    }

    /**
     * Refund.
     */
    @Override
    public void refund() {
        incrementNumberOfFreeMovies();
    }

    /**
     * Get recommendation.
     */
    @Override
    public void getRecommendation(final ArrayNode output) {
        ArrayList<Genre> genres = getGenres();
        ArrayList<Movie> movies = MoviesPage.getInstance().getMovies().getMovies();
        movies.sort((o1, o2) -> o2.getNumLikes() - o1.getNumLikes());

        movies.removeIf(movie -> getWatchedMovies().contains(movie));

        boolean foundNotification = false;
        for (Genre genre : genres) {
            for (Movie movie : movies) {
                if (movie.getGenres().contains(genre.genreName)) {
                    getNotifications().add(new Notification(movie.getName(), "Recommendation"));
                    foundNotification = true;
                    break;
                }
            }

            if (foundNotification) {
                break;
            }
        }

        if (!foundNotification) {
            getNotifications().add(new Notification("No recommendation", "Recommendation"));
        }
        output.addPOJO(new Output(this, (ArrayList<Movie>) null));
    }

    private static class Genre {
        private final String genreName;
        private int likes;

        /**
         * Instantiates a new Genre.
         *
         * @param genre the genre
         */
        Genre(final String genre) {
            this.genreName = genre;
            likes = 0;
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
            Genre genre1 = (Genre) o;
            return genreName.equals(genre1.genreName);
        }

        /**
         * HashCode.
         */
        @Override
        public int hashCode() {
            return Objects.hash(genreName);
        }
    }

    private ArrayList<Genre> getGenres() {
        ArrayList<Genre> genres = new ArrayList<>();

        for (Movie movie : getLikedMovies()) {
            for (String movieGenre : movie.getGenres()) {
                Genre newGenre = new Genre(movieGenre);
                if (genres.contains(newGenre)) {
                    int index = genres.indexOf(newGenre);
                    genres.get(index).likes++;
                } else {
                    genres.add(newGenre);
                }
            }
        }

        sortGenres(genres);
        return genres;
    }

    private void sortGenres(final ArrayList<Genre> genres) {
        genres.sort(Comparator.comparing(o -> o.genreName));
        genres.sort((o1, o2) -> o2.likes - o1.likes);
    }
}
