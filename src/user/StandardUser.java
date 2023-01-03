package user;

/**
 * The type Standard user.
 */
public class StandardUser extends User {
    /**
     * Instantiates a new Standard user.
     *
     * @param user the user
     */
    public StandardUser(final User user) {
        super(user);
        user.getCredentials().setAccountType("standard");
    }
}
