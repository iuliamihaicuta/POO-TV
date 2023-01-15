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
public final class ChangePageAction implements Action {
    /**
     * Instantiates a new Change page action.
     */
    public ChangePageAction() {
    }

    /**
     * Change page.
     *
     * @param action          the action
     * @param currentPosition the current position
     * @param output          the output
     */
    @Override
    public void execute(final ActionInput action,
                        final ArrayNode output,
                        final CurrentPosition currentPosition) {
        Page nextPage;
        nextPage = getNextPage(currentPosition, Database.getInstance().getMovies(),
                    action.getMovie(), action.getPage());

        if (nextPage != null) {
            getChangePageOutput(nextPage, Database.getInstance().getMovies(), currentPosition, output);
            nextPage.setPreviousPage(currentPosition.getCurrentPage());
            currentPosition.setCurrentPage(nextPage);
            return;
        }

        output.addPOJO(new Output());
    }

    private Page getNextPage(final CurrentPosition currentPosition,
                                    final MovieList movieList,
                                    final String movieName,
                                    final String pageName) {
        if (!currentPosition.getCurrentPage().getAccessiblePages().contains(pageName)) {
            return null;
        }

        Page page = currentPosition.getCurrentPage().getPageByName(pageName);

        if (pageName.equals("movies")) {
            String country = currentPosition.getCurrentUser().getCredentials().getCountry();
            MoviesPage.getInstance().setMovies(new MovieList(movieList.getPermittedMovies(country)));
        } else if (pageName.equals("see details")) {
            MovieList permittedMovies = MoviesPage.getInstance().getMovies();
            Movie currentMovie = permittedMovies.getMovieByName(movieName);

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
     * @param nextPage        the next page
     * @param movieList       the movie list
     * @param currentPosition the current position
     * @param output          the output
     */
    public void getChangePageOutput(final Page nextPage,
                             final MovieList movieList,
                             final CurrentPosition currentPosition,
                             final ArrayNode output) {
        switch (nextPage.getName()) {
            case "logout" -> currentPosition.setCurrentUser(null);
            case "movies" -> {
                String country = currentPosition.getCurrentUser().getCredentials().getCountry();
                MoviesPage.getInstance().setMovies(movieList.getPermittedMovies(country));

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
