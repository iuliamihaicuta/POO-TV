package io;

import action.Action;
import action.ActionFactory;
import movie.Movie;
import user.User;
import user.factory.UserFactory;

import java.util.ArrayList;

/**
 * The type Input.
 */
public final class Input {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<ActionInput> actions = new ArrayList<>();

    /**
     * Instantiates a new Input.
     */
    public Input() {
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Gets movies.
     *
     * @return the movies
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * Sets movies.
     *
     * @param movies the movies
     */
    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Gets actions.
     *
     * @return the actions
     */
    public ArrayList<ActionInput> getActions() {
        return actions;
    }

    /**
     * Sets actions.
     *
     * @param actions the actions
     */
    public void setActions(final ArrayList<ActionInput> actions) {
        this.actions = actions;
    }

    /**
     * Gets users list.
     *
     * @return the users list
     */
    public ArrayList<User> getUsersList() {
        ArrayList<User> usersList = new ArrayList<>();

        for (User user : getUsers()) {
            usersList.add(UserFactory.createUser(user.getCredentials().getAccountType(), user));
        }

        return usersList;
    }

    /**
     * Gets actions list.
     *
     * @return the actions list
     */
    public ArrayList<Action> getActionsList() {
        ArrayList<Action> actionsList = new ArrayList<>();

        for (ActionInput action : getActions()) {
            actionsList.add(ActionFactory.createAction(action));
        }

        return actionsList;
    }
}
