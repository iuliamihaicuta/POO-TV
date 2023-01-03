package action.type;

import action.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import database.Database;
import io.ActionInput;

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
            case "add"
        }

    }
}
