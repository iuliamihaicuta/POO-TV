package action.type;

import action.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import database.Database;
import io.ActionInput;
import io.Output;
import movie.Movie;
import pages.types.MoviesPage;
import user.attributes.Notification;
import user.User;

import java.util.ArrayList;

/**
 * The type Database action.
 */
public final class DatabaseAction implements Action {
    /**
     * Instantiates a new Database action.
     */
    public DatabaseAction() {
    }
    /**
     * Execute action.
     *
     * @param action          the action
     * @param output          the output
     * @param currentPosition the current position
     */
    @Override
    public void execute(final ActionInput action,
                        final ArrayNode output,
                        final CurrentPosition currentPosition) {
        switch (action.getFeature()) {
            case "add" -> addMovie(action, output);
            case "delete" -> deleteMovie(action, output);
            default -> throw new IllegalArgumentException("Unrecognized action");
        }
    }

    private void addMovie(final ActionInput action,
                          final ArrayNode output) {
        Movie movie = action.getAddedMovie();

        Database database = Database.getInstance();
        if (database.getMovies().getMovies().contains(movie)) {
            output.addPOJO(new Output());
            return;
        }

        database.getMovies().getMovies().add(movie);
        ArrayList<User> subscribers = database.getSubscribersToGenres(movie.getGenres());
        Notification notification = new Notification(movie.getName(), "ADD");

        subscribers.forEach(s -> s.getNotifications().add(notification));
    }

    private void deleteMovie(final ActionInput action,
                             final ArrayNode output) {
        Movie deletedMovie = new Movie(action.getDeletedMovie());
        Database database = Database.getInstance();

        if (!database.getMovies().getMovies().contains(deletedMovie)) {
            output.addPOJO(new Output());
            return;
        }

        Notification notification = new Notification(deletedMovie.getName(), "DELETED");

        database.getUsers().stream().filter(o -> o.removeMovie(deletedMovie)).forEach(user -> {
            user.refund();
            user.getNotifications().add(notification);
        });

        database.getMovies().getMovies().remove(deletedMovie);
        MoviesPage.getInstance().getMovies().getMovies().remove(deletedMovie);
    }
}
