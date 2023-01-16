package action.movieActions.command;

import action.movieActions.MovieActions;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * The type Like movie.
 */
public class LikeMovie implements Command {
    private final MovieActions movieActions = new MovieActions();
    private final ArrayNode output;

    /**
     * Instantiates a new Like movie.
     *
     * @param output the output
     */
    public LikeMovie(final ArrayNode output) {
        this.output = output;
    }

    /**
     * Execute.
     */
    @Override
    public void execute() {
        movieActions.likeMovie(output);
    }
}
