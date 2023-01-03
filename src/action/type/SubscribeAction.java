package action.type;

import action.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import database.Database;
import io.ActionInput;
import io.Output;

public class SubscribeAction implements Action {
    public SubscribeAction() {
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
        if (currentPosition.getCurrentPage().getName().equals("see details")
            && currentPosition.getCurrentMovie().getGenres().contains(action.getSubscribedGenre())
            && !currentPosition.getCurrentUser().getSubscribedGenres().contains(action.getSubscribedGenre())) {
            currentPosition.getCurrentUser().getSubscribedGenres().add(action.getSubscribedGenre());
            return;
        }

        output.addPOJO(new Output());
    }
}
