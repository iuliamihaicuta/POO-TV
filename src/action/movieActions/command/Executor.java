package action.movieActions.command;

/**
 * The type Executor.
 */
public class Executor {
    /**
     * Execute operation.
     *
     * @param command the command
     */
    public void executeOperation(Command command) {
        command.execute();
    }
}
