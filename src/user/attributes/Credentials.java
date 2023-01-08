package user.attributes;

import java.util.Objects;

/**
 * The type Credentials.
 */
public class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    /**
     * Instantiates a new Credentials.
     */
    public Credentials() {
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets account type.
     *
     * @return the account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets account type.
     *
     * @param accountType the account type
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public String getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(final String balance) {
        this.balance = balance;
    }

    /**
     * Overrides equals(Object o) method
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Credentials that = (Credentials) o;
        return name.equals(that.name) && password.equals(that.password);
    }

    /**
     * Overrides hashCode() methode
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
