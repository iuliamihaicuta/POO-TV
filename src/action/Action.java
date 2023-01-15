package action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionInput;

/**
 * The interface Action.
 */
public abstract class Action {
    private ActionInput actionInput;

    public Action(ActionInput actionInput) {
        this.actionInput = actionInput;
    }

    public ActionInput getActionInput() {
        return actionInput;
    }

    public void setActionInput(ActionInput actionInput) {
        this.actionInput = actionInput;
    }

    /**
     * Execute action.
     */
    public abstract void execute(final ArrayNode output);
}
