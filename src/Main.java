import action.Action;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import database.Database;
import io.Input;
import movie.MovieList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The type Main.
 */
public final class Main {
    private Main() {
    }

    /**
     * Main.
     *
     * @param args the args
     * @throws IOException the io exception
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        Input input = objectMapper.readValue(new File(args[0]), Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        Database.setInstance(new MovieList(input.getMovies()), input.getUsersList());
        ArrayList<Action> actions = input.getActionsList();

        for (Action action : actions) {
            action.execute(output);
        }

        CurrentPosition.getInstance().getCurrentUser().getRecommendation(output);

        writer.writeValue(new File(args[1]), output);

        Database.setInstance(null);
        CurrentPosition.setInstance(null);
    }
}
