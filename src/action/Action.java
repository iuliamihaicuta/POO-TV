package action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import database.Database;
import io.ActionInput;

/**
 * The interface Action.
 */
public interface Action {

    /**
     * Execute action.
     *
     * @param action          the action
     * @param database        the database
     * @param output          the output
     * @param currentPosition the current position
     */
    void execute(ActionInput action,
                 Database database,
                 ArrayNode output,
                 CurrentPosition currentPosition);
}
