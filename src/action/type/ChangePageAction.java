package action.type;

import action.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import database.Database;
import io.ActionInput;
import io.Output;
import movie.Movie;
import movie.MovieList;
import pages.types.MoviesPage;
import pages.Page;

/**
 * The type Change page action.
 */
public final class ChangePageAction extends Action {
    /**
     * Instantiates a new Change page action.
     *
     * @param actionInput the action input
     */
    public ChangePageAction(final ActionInput actionInput) {
        super(actionInput);
    }

    /**
     * Change page.
     *
     * @param output          the output
     */
    @Override
    public void execute(final ArrayNode output) {
        Page nextPage;
        nextPage = getNextPage();

        if (nextPage != null) {
            getChangePageOutput(nextPage, output);
            nextPage.setPreviousPage(CurrentPosition.getInstance().getCurrentPage());
            CurrentPosition.getInstance().setCurrentPage(nextPage);
            return;
        }

        output.addPOJO(new Output());
    }

    private Page getNextPage() {
        CurrentPosition currentPosition = CurrentPosition.getInstance();
        String pageName = getActionInput().getPage();

        if (!currentPosition.getCurrentPage().getAccessiblePages().contains(pageName)) {
            return null;
        }

        Page page = currentPosition.getCurrentPage().getPageByName(pageName);

        if (pageName.equals("movies")) {
            String country = currentPosition.getCurrentUser().getCredentials().getCountry();
            ((MoviesPage) page).setMovies(new MovieList(Database.getInstance().getMovies().getPermittedMovies(country)));
        } else if (pageName.equals("see details")) {
            MovieList permittedMovies = ((MoviesPage) CurrentPosition.getInstance().getCurrentPage()).getMovies();
            Movie currentMovie = permittedMovies.getMovieByName(getActionInput().getMovie());

            if (currentMovie == null) {
                page = null;
            } else {
                currentPosition.setCurrentMovie(currentMovie);
            }
        }

        return page;
    }

    /**
     * Gets change page output.
     *
     * @param nextPage the next page
     * @param output   the output
     */
    public void getChangePageOutput(final Page nextPage,
                             final ArrayNode output) {
        CurrentPosition currentPosition = CurrentPosition.getInstance();

        switch (nextPage.getName()) {
            case "logout" -> currentPosition.setCurrentUser(null);
            case "movies" -> {
                MovieList movieList = Database.getInstance().getMovies();
                String country = currentPosition.getCurrentUser().getCredentials().getCountry();

                ((MoviesPage) nextPage).setMovies(movieList.getPermittedMovies(country));

                output.addPOJO(new Output(currentPosition.getCurrentUser(),
                        movieList.getPermittedMovies(country).getMovies()));
            }
            case "see details" ->
                    output.addPOJO(new Output(currentPosition.getCurrentUser(),
                            currentPosition.getCurrentMovie()));
            case "login", "register", "upgrades",
                    "homepage neautentificat", "homepage autentificat" -> { }
            default -> throw new IllegalArgumentException("Unrecognized action");
        }
    }
}
