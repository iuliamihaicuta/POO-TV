package pages;

import movie.Movie;
import pages.types.AuthorisedHomepage;
import pages.types.LoginPage;
import pages.types.MoviesPage;
import pages.types.RegisterPage;
import pages.types.SeeDetailsPage;
import pages.types.UnauthHomepage;
import pages.types.UpgradesPage;

import java.util.ArrayList;

/**
 * The type Page.
 */
public abstract class Page {
    private String name;
    private ArrayList<String> accessiblePages;
    private ArrayList<String> actions;
    private Page previousPage = null;

    /**
     * Gets accessible pages.
     *
     * @return the accessible pages
     */
    public ArrayList<String> getAccessiblePages() {
        return accessiblePages;
    }

    /**
     * Sets accessible pages.
     *
     * @param accessiblePages the accessible pages
     */
    public void setAccessiblePages(final ArrayList<String> accessiblePages) {
        this.accessiblePages = accessiblePages;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets actions.
     *
     * @return the actions
     */
    public ArrayList<String> getActions() {
        return actions;
    }

    /**
     * Sets actions.
     *
     * @param actions the actions
     */
    public void setActions(final ArrayList<String> actions) {
        this.actions = actions;
    }

    /**
     * Gets previous page.
     *
     * @return the previous page
     */
    public Page getPreviousPage() {
        return previousPage;
    }

    /**
     * Sets previous page.
     *
     * @param previousPage the previous page
     */
    public void setPreviousPage(final Page previousPage) {
        this.previousPage = previousPage;
    }

    /**
     * Gets page by name.
     *
     * @param pageName the page name
     * @return the page by name
     */
    public Page getPageByName(final String pageName) {
        switch (pageName) {
            case "login" -> {
                return new LoginPage();
            }
            case "register" -> {
                return new RegisterPage();
            }
            case "homepage neautentificat", "logout" -> {
                return new UnauthHomepage();
            }
            case "homepage autentificat" -> {
                return new AuthorisedHomepage();
            }
            case "movies" -> {
                return new MoviesPage();
            }
            case "upgrades" -> {
                return new UpgradesPage();
            }
            case "see details" -> {
                return new SeeDetailsPage();
            }
            default -> throw new IllegalArgumentException("Unrecognized page");
        }
    }

    /**
     * Remove movie.
     *
     * @param movie the movie
     */
    public void removeMovie(final Movie movie) {
    }
}
