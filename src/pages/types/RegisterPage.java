package pages.types;

import pages.Page;

import java.util.ArrayList;

/**
 * The type Register page.
 */
public final class RegisterPage extends Page {
    /**
     * Instantiates a new Register page.
     */
    public RegisterPage() {
        this.setName("register");

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("homepage autentificat");

        this.setActions(new ArrayList<>());
        this.getActions().add("Register");
    }
}
