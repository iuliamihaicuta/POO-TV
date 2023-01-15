package action.movieActions.command;

import action.movieActions.MovieActions;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class RateMovie implements Command {
    private final MovieActions movieActions = new MovieActions();
    private final ArrayNode output;

    public RateMovie(final ArrayNode output) {
        this.output = output;
    }

    @Override
    public void execute() {
        movieActions.rateMovie(output);
    }
}
