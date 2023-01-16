package pages.types;

import pages.Page;

import java.util.ArrayList;

/**
 * The type Unauth homepage.
 */
public final  class UnauthHomepage extends Page {
    /**
     * Instantiates a new Unauth homepage.
     */
    public UnauthHomepage() {
        this.setName("homepage neautentificat");

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("login");
        this.getAccessiblePages().add("register");
    }
}
