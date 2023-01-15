package action.type;

import action.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import io.ActionInput;
import io.Output;

/**
 * The type Back action.
 */
public final class BackAction extends Action {

    /**
     * Instantiates a new Back action.
     */
    public BackAction(ActionInput actionInput) {
        super(actionInput);
    }

    /**
     * Execute action.
     * @param output          the output
     */
    @Override
    public void execute(final ArrayNode output) {
        if (CurrentPosition.getInstance().getCurrentUser() != null
                && CurrentPosition.getInstance().getCurrentPage().getPreviousPage() != null) {
            changePage(output);
            return;
        }

        output.addPOJO(new Output());
    }

    private void changePage(final ArrayNode output) {
        ChangePageAction changePage = new ChangePageAction(getActionInput());
        changePage.getChangePageOutput(CurrentPosition.getInstance().getCurrentPage().getPreviousPage(), output);
        CurrentPosition.getInstance().setCurrentPage(CurrentPosition.getInstance().getCurrentPage().getPreviousPage());
    }
}
