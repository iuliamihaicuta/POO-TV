package pages.types;

import pages.Page;

import java.util.ArrayList;

/**
 * The type Upgrades page.
 */
public final class UpgradesPage extends Page {
    /**
     * Instantiates a new Upgrades page.
     */
    public UpgradesPage() {
        this.setName("upgrades");

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("homepage autentificat");
        this.getAccessiblePages().add("movies");
        this.getAccessiblePages().add("logout");
        this.getAccessiblePages().add("upgrades");

        this.setActions(new ArrayList<>());
        this.getActions().add("Buy premium account");
        this.getActions().add("Buy tokens");
    }
}
