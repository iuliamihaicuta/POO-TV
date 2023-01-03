package pages;

import java.util.ArrayList;

/**
 * The type Login page.
 */
public final class LoginPage extends Page {
    private static LoginPage instance = null;

    private LoginPage() {
        this.setName("login");

        this.setActions(new ArrayList<>());

        this.setAccessiblePages(new ArrayList<>());
        this.getAccessiblePages().add("homepage autentificat");
        this.getActions().add("login");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static LoginPage getInstance() {
        if (instance == null) {
            instance = new LoginPage();
        }
        return instance;
    }
}
