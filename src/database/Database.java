package database;

import movie.MovieList;
import user.User;

import java.util.ArrayList;

/**
 * The type Database.
 */
public class Database {
    private MovieList movies;
    private ArrayList<User> users;
    private static Database instance = null;

    /**
     * Instantiates a new Database.
     *
     * @param movies the movies
     * @param users  the users
     */
    private Database(final MovieList movies,
                     final ArrayList<User> users) {
        this.movies = movies;
        this.users = users;
    }

    private Database() {
    }

    /**
     * Gets movies.
     *
     * @return the movies
     */
    public MovieList getMovies() {
        return movies;
    }

    /**
     * Sets movies.
     *
     * @param movies the movies
     */
    public void setMovies(final MovieList movies) {
        this.movies = movies;
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
     * Gets subscribers to genres.
     *
     * @param genres the genres
     * @return the subscribers to genres
     */
    public ArrayList<User> getSubscribersToGenres(final ArrayList<String> genres) {
        ArrayList<User> subscribers = new ArrayList<>();

        for (String genre : genres) {
            for (User user : users) {
                if (user.getSubscribedGenres().contains(genre) && !subscribers.contains(user)) {
                    subscribers.add(user);
                }
            }
        }

        return subscribers;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    /**
     * Sets instance.
     *
     * @param database the database
     */
    public static void setInstance(final Database database) {
        instance = database;
    }

    /**
     * Sets instance.
     *
     * @param movies the movies
     * @param users  the users
     */
    public static void setInstance(final MovieList movies,
                                   final ArrayList<User> users) {
        instance = new Database(movies, users);
    }
}
