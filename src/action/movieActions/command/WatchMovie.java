package action.movieActions.command;

import action.movieActions.MovieActions;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * The type Watch movie.
 */
public class WatchMovie implements Command {
    private final MovieActions movieActions = new MovieActions();
    private final ArrayNode output;

    /**
     * Instantiates a new Watch movie.
     *
     * @param output the output
     */
    public WatchMovie(final ArrayNode output) {
        this.output = output;
    }

    /**
     * Execute.
     */
    @Override
    public void execute() {
        movieActions.watchMovie(output);
    }
}
