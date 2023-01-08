package user.factory;

import user.User;

/**
 * The type User factory.
 */
public final class UserFactory {
    private UserFactory() {
    }

    /**
     * Create user of userType.
     *
     * @param userType the user type
     * @param user     the user
     * @return the user
     */
    public static User createUser(final String userType,
                                  final User user) {
        switch (userType) {
            case "standard" -> {
                return new StandardUser(user);
            }
            case "premium" -> {
                return new PremiumUser(user);
            }
            default -> throw new IllegalArgumentException("Unrecognized user type");
        }
    }
}
