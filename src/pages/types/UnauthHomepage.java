package pages.types;

import pages.Page;

import java.util.ArrayList;

/**
 * The type Unauth homepage.
 */
public final  class UnauthHomepage extends Page {
    private static UnauthHomepage instance = null;

    private UnauthHomepage() {
        this.setName("homepage neautentificat");

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("login");
        this.getAccessiblePages().add("register");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UnauthHomepage getInstance() {
        if (instance == null) {
            instance = new UnauthHomepage();
        }
        return instance;
    }
}
