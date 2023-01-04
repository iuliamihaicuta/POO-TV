package user;

import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * The type Premium user.
 */
public class PremiumUser extends User {
    /**
     * Instantiates a new Premium user.
     *
     * @param user the user
     */
    public PremiumUser(final User user) {
        super(user);
        this.getCredentials().setAccountType("premium");
    }

    @Override
    public void refund() {
        incrementNumberOfFreeMovies();
    }

    @Override
    public void getRecommendation(ArrayNode output) {

    }
}
