package action.movieActions.command;

import action.movieActions.MovieActions;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * The type Rate movie.
 */
public class RateMovie implements Command {
    private final MovieActions movieActions = new MovieActions();
    private final ArrayNode output;

    /**
     * Instantiates a new Rate movie.
     *
     * @param output the output
     */
    public RateMovie(final ArrayNode output) {
        this.output = output;
    }

    /**
     * Execute.
     */
    @Override
    public void execute() {
        movieActions.rateMovie(output);
    }
}
