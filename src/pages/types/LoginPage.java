package pages.types;

import pages.Page;

import java.util.ArrayList;

/**
 * The type Login page.
 */
public final class LoginPage extends Page {
    /**
     * Instantiates a new Login page.
     */
    public LoginPage() {
        this.setName("login");

        this.setActions(new ArrayList<>());

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("homepage autentificat");
        this.getActions().add("login");
    }
}
