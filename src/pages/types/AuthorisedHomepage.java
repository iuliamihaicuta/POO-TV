package pages.types;

import pages.Page;

import java.util.ArrayList;

/**
 * The type Authorised homepage.
 */
public final class AuthorisedHomepage extends Page {
    public AuthorisedHomepage() {
        this.setName("homepage autentificat");

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("movies");
        this.getAccessiblePages().add("upgrades");
        this.getAccessiblePages().add("logout");
    }
}
