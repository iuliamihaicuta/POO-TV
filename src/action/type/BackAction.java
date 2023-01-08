package action.type;

import action.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import database.Database;
import io.ActionInput;
import io.Output;

/**
 * The type Back action.
 */
public final class BackAction implements Action {

    /**
     * Instantiates a new Back action.
     */
    public BackAction() {
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
        if (currentPosition.getCurrentUser() != null
                && currentPosition.getCurrentPage().getPreviousPage() != null) {
            changePage(database, output, currentPosition);
            return;
        }

        output.addPOJO(new Output());
    }

    private void changePage(final Database database,
                            final ArrayNode output,
                            final CurrentPosition currentPosition) {
        ChangePageAction changePage = new ChangePageAction();
        changePage.getChangePageOutput(currentPosition.getCurrentPage().getPreviousPage(),
                database.getMovies(), currentPosition, output);
        currentPosition.setCurrentPage(currentPosition.getCurrentPage().getPreviousPage());
    }
}
