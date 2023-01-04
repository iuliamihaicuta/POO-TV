package action.type;

import action.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import database.Database;
import io.ActionInput;
import io.Output;
import movie.Movie;
import pages.types.MoviesPage;
import user.Notification;
import user.User;

import java.util.ArrayList;

public final class DatabaseAction implements Action {
    public DatabaseAction() {
    }
    /**
     * Execute action.
     *
     * @param action          the action
     * @param database        the database
     * @param output          the output
     * @param currentPosition the current position
     */
    @Override
    public void execute(final ActionInput action,
                        final Database database,
                        final ArrayNode output,
                        final CurrentPosition currentPosition) {
        switch (action.getFeature()) {
            case "add" -> addMovie(action, database, output);
            case "delete" -> deleteMovie(action, database, output, currentPosition);
            default -> throw new IllegalArgumentException("Unrecognized action");
        }

    }

    private void addMovie(final ActionInput action,
                          final Database database,
                          ArrayNode output) {
        Movie movie = action.getAddedMovie();

        if (database.getMovies().getMovies().contains(movie)) {
            output.addPOJO(new Output());
            return;
        }

        database.getMovies().getMovies().add(movie);
        ArrayList<User> subscribers = database.getSubscribersToGenres(movie.getGenres());
        Notification notification = new Notification(movie.getName(), "ADD");
    }

    private void deleteMovie(final ActionInput action,
                             final Database database,
                             ArrayNode output,
                             final CurrentPosition currentPosition) {
        Movie deletedMovie = new Movie(action.getDeletedMovie());
        if (!database.getMovies().getMovies().contains(deletedMovie)) {
            output.addPOJO(new Output());
            return;
        }

        Notification notification = new Notification(deletedMovie.getName(), "DELETED");

        for (User user : database.getUsers()) {
            if (user.removeMovie(deletedMovie)) {
                user.refund();
                user.getNotifications().add(notification);
            }
        }

        database.getMovies().getMovies().remove(deletedMovie);
        MoviesPage.getInstance().getMovies().getMovies().remove(deletedMovie);
//        if (currentPosition.getCurrentMovie().equals(deletedMovie)) {
//            currentPosition.setCurrentMovie(null);
//        }
    }
}
