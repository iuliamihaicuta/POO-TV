package action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import io.ActionInput;

/**
 * The interface Action.
 */
public interface Action {

    /**
     * Execute action.
     *
     * @param action          the action
     * @param output          the output
     * @param currentPosition the current position
     */
    void execute(ActionInput action,
                 ArrayNode output,
                 CurrentPosition currentPosition);
}
