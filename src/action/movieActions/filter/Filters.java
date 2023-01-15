package action.movieActions.filter;

/**
 * The type Filters.
 */
public class Filters {
    private Sort sort;
    private Contains contains;

    /**
     * Gets sort.
     *
     * @return the sort
     */
    public Sort getSort() {
        return sort;
    }

    /**
     * Sets sort.
     *
     * @param sort the sort
     */
    public void setSort(final Sort sort) {
        this.sort = sort;
    }

    /**
     * Gets contains.
     *
     * @return the contains
     */
    public Contains getContains() {
        return contains;
    }

    /**
     * Sets contains.
     *
     * @param contains the contains
     */
    public void setContains(final Contains contains) {
        this.contains = contains;
    }
}
