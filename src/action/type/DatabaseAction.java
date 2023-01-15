package action.type;

import action.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import io.ActionInput;
import io.Output;
import movie.Movie;
import pages.types.MoviesPage;
import user.User;
import user.attributes.Notification;

import java.util.ArrayList;

/**
 * The type Database action.
 */
public final class DatabaseAction extends Action {
    /**
     * Instantiates a new Database action.
     */
    public DatabaseAction(ActionInput actionInput) {
        super(actionInput);
    }
    /**
     * Execute action.
     *
     * @param output          the output
     */
    @Override
    public void execute(final ArrayNode output) {
        switch (getActionInput().getFeature()) {
            case "add" -> addMovie(output);
            case "delete" -> deleteMovie(output);
            default -> throw new IllegalArgumentException("Unrecognized action");
        }
    }

    private void addMovie(final ArrayNode output) {
        Movie movie = getActionInput().getAddedMovie();

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

    private void deleteMovie(final ArrayNode output) {
        Movie deletedMovie = new Movie(getActionInput().getDeletedMovie());
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
