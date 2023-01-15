package action.type;

import action.Action;
import action.MovieActions;
import database.Database;
import io.ActionInput;
import io.Output;
import action.filter.Contains;
import action.filter.Sort;
import com.fasterxml.jackson.databind.node.ArrayNode;
import movie.Movie;
import movie.MovieList;
import pages.types.AuthorisedHomepage;
import pages.types.MoviesPage;
import pages.types.UnauthHomepage;
import user.attributes.Credentials;
import currentPosition.CurrentPosition;
import user.factory.PremiumUser;
import user.User;
import user.factory.UserFactory;

import java.util.ArrayList;
import static constants.Constants.PREMIUM_ACCOUNT_PRICE;

/**
 * The type On page action.
 */
public final class OnPageAction extends Action {
    /**
     * Instantiates a new On page action.
     */
    public OnPageAction(ActionInput actionInput) {
        super(actionInput);
    }

    /**
     * Executes on page action.
     *
     * @param output          the output
     */
    @Override
    public void execute(final ArrayNode output) {
        ArrayList<User> users = Database.getInstance().getUsers();
        switch (getActionInput().getFeature()) {
            case "login" -> {
                login(getActionInput(), users, output, CurrentPosition.getInstance());
                return;
            }
            case "register" -> {
                register(getActionInput(), users, output, CurrentPosition.getInstance());
                return;
            }
            default -> { }
        }

        Credentials userCredentials = CurrentPosition.getInstance().getCurrentUser().getCredentials();
        MovieList movieList = Database.getInstance().getMovies();
        MovieList permittedMovies = movieList.getPermittedMovies(userCredentials.getCountry());
        switch (getActionInput().getFeature()) {
            case "search" -> search(getActionInput(), permittedMovies, output, CurrentPosition.getInstance());
            case "filter" -> filter(getActionInput(), permittedMovies, output, CurrentPosition.getInstance());
            case "purchase", "watch", "like", "rate" ->
                    seeDetailsActions(output, CurrentPosition.getInstance(), getActionInput());
            case "buy tokens" -> buyTokens(output, CurrentPosition.getInstance(), getActionInput());
            case "buy premium account" -> buyPremiumAccount(output, CurrentPosition.getInstance());
            case "subscribe" -> subscribe(getActionInput(), CurrentPosition.getInstance(), output);
            default -> throw new IllegalArgumentException("Unrecognized action");
        }
    }

    private void login(final ActionInput action,
                              final ArrayList<User> users,
                              final ArrayNode output,
                              final CurrentPosition currentPosition) {
        if (!currentPosition.getCurrentPage().getName().equals("login")) {
            output.addPOJO(new Output());
            return;
        }

        User newUser = null;
        for (User user : users) {
            if (action.getCredentials().equals(user.getCredentials())) {
                newUser = user;
                break;
            }
        }

        if (newUser == null) {
            output.addPOJO(new Output());

            currentPosition.setCurrentPage(UnauthHomepage.getInstance());
            return;
        }

        currentPosition.setCurrentUser(newUser);
        currentPosition.setCurrentPage(AuthorisedHomepage.getInstance());
        output.addPOJO(new Output(currentPosition.getCurrentUser()));
    }

    private void register(final ActionInput action,
                                 final ArrayList<User> users,
                                 final ArrayNode output,
                                 final CurrentPosition currentPosition) {
        for (User user : users) {
            if (action.getCredentials().equals(user.getCredentials())) {
                output.addPOJO(new Output());
                return;
            }
        }

        User newUser = UserFactory.createUser(action.getCredentials().getAccountType(),
                new User(action.getCredentials()));
        users.add(newUser);

        currentPosition.setCurrentUser(newUser);
        currentPosition.setCurrentPage(AuthorisedHomepage.getInstance());
        output.addPOJO(new Output(currentPosition.getCurrentUser()));
    }

    private void search(final ActionInput action,
                               final MovieList movieList,
                               final ArrayNode output,
                               final CurrentPosition currentPosition) {
        if (!currentPosition.getCurrentPage().getName().equals("movies")) {
            output.addPOJO(new Output());
            return;
        }

        MovieList newMovieList = new MovieList();

        movieList.getMovies().stream().filter(movie ->
                movie.getName().startsWith(action.getStartsWith())).forEach(movie ->
                newMovieList.getMovies().add(movie));
        MoviesPage.getInstance().setMovies(new MovieList(newMovieList));

        output.addPOJO(new Output(currentPosition.getCurrentUser(), newMovieList.getMovies()));
    }

    private void filter(final ActionInput action,
                        final MovieList movieList,
                        final ArrayNode output,
                         final CurrentPosition currentPosition) {
        if (!currentPosition.getCurrentPage().getName().equals("movies")) {
            output.addPOJO(new Output());
            return;
        }

        MoviesPage.getInstance().setMovies(movieList);

        if (action.getFilters().getContains() != null) {
            Contains contains = action.getFilters().getContains();
            MoviesPage.getInstance().getMovies().moviesContain(contains);
        }

        if (action.getFilters().getSort() != null) {
            Sort sort = action.getFilters().getSort();
            MoviesPage.getInstance().getMovies().sortMovies(sort);
        }

        output.addPOJO(new Output(currentPosition.getCurrentUser(),
                MoviesPage.getInstance().getMovies().getMovies()));
    }

    private void seeDetailsActions(final ArrayNode output,
                                  final CurrentPosition currentPosition,
                                  final ActionInput action) {
        if (!currentPosition.getCurrentPage().getName().equals("see details")) {
            output.addPOJO(new Output());
            return;
        }

        Movie movie = currentPosition.getCurrentMovie();
        MovieActions movieAction = new MovieActions();

        if (movie != null) {
            switch (action.getFeature()) {
                case "purchase" -> movieAction.purchaseMovie(movie,
                                currentPosition.getCurrentUser(), output);
                case "watch" -> movieAction.watchMovie(movie,
                                currentPosition.getCurrentUser(), output);
                case "like" -> movieAction.likeMovie(movie,
                                currentPosition.getCurrentUser(), output);
                case "rate" -> movieAction.rateMovie(action.getRate(), movie,
                                currentPosition.getCurrentUser(), output);
                default -> throw new IllegalArgumentException("Unrecognized action");
            }

            return;
        }

        output.addPOJO(new Output());
    }

    private void buyTokens(final ArrayNode output,
                                  final CurrentPosition currentPosition,
                                  final ActionInput action) {
        if (!currentPosition.getCurrentPage().getName().equals("upgrades")) {
            output.addPOJO(new Output());
            return;
        }

        User user = currentPosition.getCurrentUser();
        int userBalance = Integer.parseInt(user.getCredentials().getBalance());

        if (userBalance < action.getCount()) {
            output.addPOJO(new Output());
            return;
        }

        user.getCredentials().setBalance(String.valueOf(userBalance - action.getCount()));
        user.setTokensCount(user.getTokensCount() + action.getCount());
    }

    private void buyPremiumAccount(final ArrayNode output,
                                   final CurrentPosition currentPosition) {
        if (!currentPosition.getCurrentPage().getName().equals("upgrades")) {
            output.addPOJO(new Output());
            return;
        }

        User user = currentPosition.getCurrentUser();
        int userTokens = user.getTokensCount();

        if (userTokens < PREMIUM_ACCOUNT_PRICE
                || user.getCredentials().getAccountType().equals("premium")) {
            output.addPOJO(new Output());
            return;
        }

        user.setTokensCount(userTokens - PREMIUM_ACCOUNT_PRICE);

        User newUser = new PremiumUser(user);
        currentPosition.setCurrentUser(newUser);
        Database.getInstance().getUsers().remove(user);
        Database.getInstance().getUsers().add(newUser);

    }

    private void subscribe(final ActionInput action,
                           final CurrentPosition currentPosition,
                           final ArrayNode output) {
        if (currentPosition.getCurrentPage().getName().equals("see details")
                && currentPosition.getCurrentMovie().getGenres().contains(action.getSubscribedGenre())
                && !currentPosition.getCurrentUser().getSubscribedGenres().contains(action.getSubscribedGenre())) {
            currentPosition.getCurrentUser().getSubscribedGenres().add(action.getSubscribedGenre());
            return;
        }

        output.addPOJO(new Output());
    }
}
