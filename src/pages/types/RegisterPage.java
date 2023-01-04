package pages.types;

import pages.Page;

import java.util.ArrayList;

/**
 * The type Register page.
 */
public final class RegisterPage extends Page {
    private static RegisterPage instance = null;

    private RegisterPage() {
        this.setName("register");

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("homepage autentificat");

        this.setActions(new ArrayList<>());
        this.getActions().add("Register");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RegisterPage getInstance() {
        if (instance == null) {
            instance = new RegisterPage();
        }
        return instance;
    }
}
