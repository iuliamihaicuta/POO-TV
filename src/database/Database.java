package database;

import movie.MovieList;
import user.User;

import java.util.ArrayList;

public class Database {
    private MovieList movies;
    private ArrayList<User> users;

    public Database() {
    }

    public Database(MovieList movies, ArrayList<User> users) {
        this.movies = movies;
        this.users = users;
    }

    public MovieList getMovies() {
        return movies;
    }

    public void setMovies(MovieList movies) {
        this.movies = movies;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getSubscribersToGenres(ArrayList<String> genres) {
        ArrayList<User> subscribers = new ArrayList<>();

        for (String genre : genres) {
            for (User user : users) {
                if (user.getSubscribedGenres().contains(genre))
                    subscribers.add(user);
            }
        }

        return subscribers;
    }
}
