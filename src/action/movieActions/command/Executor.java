package action.movieActions.command;

public class Executor {
    public void executeOperation(Command command) {
        command.execute();
    }
}
