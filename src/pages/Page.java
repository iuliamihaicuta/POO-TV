package pages;

import pages.types.*;

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

    public Page getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Page previousPage) {
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
                return LoginPage.getInstance();
            }
            case "register" -> {
                return RegisterPage.getInstance();
            }
            case "homepage neautentificat", "logout" -> {
                return UnauthHomepage.getInstance();
            }
            case "homepage autentificat" -> {
                return AuthorisedHomepage.getInstance();
            }
            case "movies" -> {
                return MoviesPage.getInstance();
            }
            case "upgrades" -> {
                return UpgradesPage.getInstance();
            }
            case "see details" -> {
                return SeeDetailsPage.getInstance();
            }
            default -> throw new IllegalArgumentException("Unrecognized page");
        }
    }
}
