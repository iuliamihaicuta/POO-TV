package action.type;

import action.Action;
import action.MovieActions;
import action.filter.Contains;
import action.filter.Sort;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currentPosition.CurrentPosition;
import database.Database;
import io.ActionInput;
import io.Output;
import movie.Movie;
import movie.MovieList;
import pages.Page;
import pages.types.AuthorisedHomepage;
import pages.types.MoviesPage;
import pages.types.UnauthHomepage;
import user.User;
import user.attributes.Credentials;
import user.factory.PremiumUser;
import user.factory.UserFactory;

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
        switch (getActionInput().getFeature()) {
            case "login" -> {
                login(output);
                return;
            }
            case "register" -> {
                register(output);
                return;
            }
            default -> { }
        }

        Credentials userCredentials = CurrentPosition.getInstance().getCurrentUser().getCredentials();
        MovieList movieList = Database.getInstance().getMovies();
        MovieList permittedMovies = movieList.getPermittedMovies(userCredentials.getCountry());
        switch (getActionInput().getFeature()) {
            case "search" -> search(permittedMovies, output);
            case "filter" -> filter(permittedMovies, output);
            case "purchase", "watch", "like", "rate" ->
                    seeDetailsActions(output);
            case "buy tokens" -> buyTokens(output);
            case "buy premium account" -> buyPremiumAccount(output);
            case "subscribe" -> subscribe(output);
            default -> throw new IllegalArgumentException("Unrecognized action");
        }
    }

    private void login(final ArrayNode output) {
        CurrentPosition currentPosition = CurrentPosition.getInstance();
        if (!currentPosition.getCurrentPage().getName().equals("login")) {
            output.addPOJO(new Output());
            return;
        }

        User newUser = null;
        for (User user : Database.getInstance().getUsers()) {
            if (getActionInput().getCredentials().equals(user.getCredentials())) {
                newUser = user;
                break;
            }
        }

        if (newUser == null) {
            output.addPOJO(new Output());

            currentPosition.setCurrentPage(new UnauthHomepage());
            return;
        }

        currentPosition.setCurrentUser(newUser);
        currentPosition.setCurrentPage(new AuthorisedHomepage());
        output.addPOJO(new Output(currentPosition.getCurrentUser()));
    }

    private void register(final ArrayNode output) {
        for (User user : Database.getInstance().getUsers()) {
            if (getActionInput().getCredentials().equals(user.getCredentials())) {
                output.addPOJO(new Output());
                return;
            }
        }

        User newUser = UserFactory.createUser(getActionInput().getCredentials().getAccountType(),
                new User(getActionInput().getCredentials()));
        Database.getInstance().getUsers().add(newUser);


        CurrentPosition currentPosition = CurrentPosition.getInstance();
        currentPosition.setCurrentUser(newUser);
        currentPosition.setCurrentPage(new AuthorisedHomepage());
        output.addPOJO(new Output(currentPosition.getCurrentUser()));
    }

    private void search(final MovieList movieList,
                        final ArrayNode output) {
        CurrentPosition currentPosition = CurrentPosition.getInstance();
        if (!currentPosition.getCurrentPage().getName().equals("movies")) {
            output.addPOJO(new Output());
            return;
        }

        MovieList newMovieList = new MovieList();

        movieList.getMovies().stream().filter(movie ->
                movie.getName().startsWith(getActionInput().getStartsWith())).forEach(movie ->
                newMovieList.getMovies().add(movie));
        ((MoviesPage) currentPosition.getCurrentPage()).setMovies(new MovieList(newMovieList));

        output.addPOJO(new Output(currentPosition.getCurrentUser(), newMovieList.getMovies()));
    }

    private void filter(final MovieList movieList,
                        final ArrayNode output) {
        CurrentPosition currentPosition = CurrentPosition.getInstance();
        if (!currentPosition.getCurrentPage().getName().equals("movies")) {
            output.addPOJO(new Output());
            return;
        }

        Page page = currentPosition.getCurrentPage();
        ((MoviesPage) page).setMovies(movieList);

        if (getActionInput().getFilters().getContains() != null) {
            Contains contains = getActionInput().getFilters().getContains();
            ((MoviesPage) page).getMovies().moviesContain(contains);
        }

        if (getActionInput().getFilters().getSort() != null) {
            Sort sort = getActionInput().getFilters().getSort();
            ((MoviesPage) page).getMovies().sortMovies(sort);
        }

        output.addPOJO(new Output(currentPosition.getCurrentUser(),
                ((MoviesPage) page).getMovies().getMovies()));
    }

    private void seeDetailsActions(final ArrayNode output) {
        CurrentPosition currentPosition = CurrentPosition.getInstance();
        if (!currentPosition.getCurrentPage().getName().equals("see details")) {
            output.addPOJO(new Output());
            return;
        }

        Movie movie = currentPosition.getCurrentMovie();
        MovieActions movieAction = new MovieActions();

        if (movie != null) {
            switch (getActionInput().getFeature()) {
                case "purchase" -> movieAction.purchaseMovie(output);
                case "watch" -> movieAction.watchMovie(output);
                case "like" -> movieAction.likeMovie(output);
                case "rate" -> movieAction.rateMovie(getActionInput().getRate(), output);
                default -> throw new IllegalArgumentException("Unrecognized action");
            }

            return;
        }

        output.addPOJO(new Output());
    }

    private void buyTokens(final ArrayNode output) {
        CurrentPosition currentPosition = CurrentPosition.getInstance();
        if (!currentPosition.getCurrentPage().getName().equals("upgrades")) {
            output.addPOJO(new Output());
            return;
        }

        User user = currentPosition.getCurrentUser();
        int userBalance = Integer.parseInt(user.getCredentials().getBalance());

        if (userBalance < getActionInput().getCount()) {
            output.addPOJO(new Output());
            return;
        }

        user.getCredentials().setBalance(String.valueOf(userBalance - getActionInput().getCount()));
        user.setTokensCount(user.getTokensCount() + getActionInput().getCount());
    }

    private void buyPremiumAccount(final ArrayNode output) {
        CurrentPosition currentPosition = CurrentPosition.getInstance();
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

    private void subscribe(final ArrayNode output) {
        CurrentPosition currentPosition = CurrentPosition.getInstance();
        if (currentPosition.getCurrentPage().getName().equals("see details")
                && currentPosition.getCurrentMovie().getGenres().contains(getActionInput().getSubscribedGenre())
                && !currentPosition.getCurrentUser().getSubscribedGenres().contains(getActionInput().getSubscribedGenre())) {
            currentPosition.getCurrentUser().getSubscribedGenres().add(getActionInput().getSubscribedGenre());
            return;
        }

        output.addPOJO(new Output());
    }
}
