package pages;

import java.util.ArrayList;

/**
 * The type Upgrades page.
 */
public final class UpgradesPage extends Page {
    private static UpgradesPage instance = null;

    private UpgradesPage() {
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

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UpgradesPage getInstance() {
        if (instance == null) {
            instance = new UpgradesPage();
        }

        return instance;
    }
}
