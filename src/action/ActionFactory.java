package action;

import action.type.ChangePageAction;
import action.type.OnPageAction;
import action.type.DatabaseAction;
import action.type.BackAction;
import io.ActionInput;

/**
 * The type Action factory.
 */
public final class ActionFactory {
    private ActionFactory() {
    }

    /**
     * Create action based on type.
     *
     * @param action the action
     * @return the action
     */
    public static Action createAction(final ActionInput action) {
        switch (action.getType()) {
            case "on page" -> {
                return new OnPageAction();
            }
            case "change page" -> {
                return new ChangePageAction();
            }
            case "back" -> {
                return new BackAction();
            }
            case "database" -> {
                return new DatabaseAction();
            }
            default -> throw new IllegalArgumentException("Unrecognized action type");
        }
    }
}
