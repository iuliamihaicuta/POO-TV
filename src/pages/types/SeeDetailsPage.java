package pages.types;

import pages.Page;

import java.util.ArrayList;

/**
 * The type See details page.
 */
public final class SeeDetailsPage extends Page {
    /**
     * Instantiates a new See details page.
     */
    public SeeDetailsPage() {
        this.setName("see details");

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("homepage autentificat");
        this.getAccessiblePages().add("movies");
        this.getAccessiblePages().add("upgrades");
        this.getAccessiblePages().add("logout");
        this.getAccessiblePages().add("see details");

        this.setActions(new ArrayList<>());
        this.getActions().add("Purchase");
        this.getActions().add("Watch");
        this.getActions().add("Like");
        this.getActions().add("Rate the movie");
    }
}
