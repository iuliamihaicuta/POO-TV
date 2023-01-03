package action.filter;

import java.util.ArrayList;

/**
 * The type Contains.
 */
public class Contains {
    private ArrayList<String> actors = new ArrayList<>();
    private ArrayList<String> genre = new ArrayList<>();

    /**
     * Instantiates a new Contains.
     */
    Contains() {
    }

    /**
     * Gets actors.
     *
     * @return the actors
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * Sets actors.
     *
     * @param actors the actors
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public ArrayList<String> getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }
}
