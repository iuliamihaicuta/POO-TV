package user.factory;

import user.User;

/**
 * The type Standard user.
 */
public final class StandardUser extends User {
    /**
     * Instantiates a new Standard user.
     *
     * @param user the user
     */
    public StandardUser(final User user) {
        super(user);
        user.getCredentials().setAccountType("standard");
    }

    /**
     * Refund.
     */
    @Override
    public void refund() {
        incrementTokensCount(2);
    }
}
