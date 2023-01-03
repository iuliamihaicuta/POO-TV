package pages;

import java.util.ArrayList;

/**
 * The type See details page.
 */
public final class SeeDetailsPage extends Page {
    private static SeeDetailsPage instance = null;
    private SeeDetailsPage() {
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

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SeeDetailsPage getInstance() {
        if (instance == null) {
            instance = new SeeDetailsPage();
        }

        return instance;
    }
}
