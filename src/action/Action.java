package action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionInput;

/**
 * The interface Action.
 */
public abstract class Action {
    private final ActionInput actionInput;

    public Action(ActionInput actionInput) {
        this.actionInput = actionInput;
    }

    public ActionInput getActionInput() {
        return actionInput;
    }

    /**
     * Execute action.
     */
    public abstract void execute(final ArrayNode output);
}
