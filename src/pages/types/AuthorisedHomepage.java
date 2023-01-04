package pages.types;

import pages.Page;

import java.util.ArrayList;

/**
 * The type Authorised homepage.
 */
public final class AuthorisedHomepage extends Page {
    private static AuthorisedHomepage instance = null;
    private AuthorisedHomepage() {
        this.setName("homepage autentificat");

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("movies");
        this.getAccessiblePages().add("upgrades");
        this.getAccessiblePages().add("logout");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AuthorisedHomepage getInstance() {
        if (instance == null) {
            instance = new AuthorisedHomepage();
        }

        return instance;
    }

}
