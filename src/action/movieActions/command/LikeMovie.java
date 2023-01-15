package action.movieActions.command;

import action.movieActions.MovieActions;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class LikeMovie implements Command {
    private final MovieActions movieActions = new MovieActions();
    private final ArrayNode output;

    public LikeMovie(final ArrayNode output) {
        this.output = output;
    }

    @Override
    public void execute() {
        movieActions.likeMovie(output);
    }
}
