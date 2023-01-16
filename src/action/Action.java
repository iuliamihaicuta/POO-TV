package action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionInput;

/**
 * The interface Action.
 */
public abstract class Action {
    private final ActionInput actionInput;

    /**
     * Instantiates a new Action.
     *
     * @param actionInput the action input
     */
    public Action(ActionInput actionInput) {
        this.actionInput = actionInput;
    }

    /**
     * Gets action input.
     *
     * @return the action input
     */
    public ActionInput getActionInput() {
        return actionInput;
    }

    /**
     * Execute action.
     *
     * @param output the output
     */
    public abstract void execute(final ArrayNode output);
}
