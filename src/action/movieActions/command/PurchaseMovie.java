package action.movieActions.command;

import action.movieActions.MovieActions;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * The type Purchase movie.
 */
public class PurchaseMovie implements Command {
    private final MovieActions movieActions = new MovieActions();
    private final ArrayNode output;

    /**
     * Instantiates a new Purchase movie.
     *
     * @param output the output
     */
    public PurchaseMovie(final ArrayNode output) {
        this.output = output;
    }

    /**
     * Execute.
     */
    @Override
    public void execute() {
        movieActions.purchaseMovie(output);
    }
}
