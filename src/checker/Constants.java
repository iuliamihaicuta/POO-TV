package checker;

/**
 * The class contains the minimum of constants needed.
 * <p>
 * You can define your own constants here or create separate files.
 */
public final class Constants {



    private Constants() {
    }

    /**
     * The constant BIG_TEST_POINTS.
     */
// checker constants
    public static final Integer BIG_TEST_POINTS = 3;
    /**
     * The constant MAXIMUM_ERROR_CHECKSTYLE.
     */
    public static final Integer MAXIMUM_ERROR_CHECKSTYLE = 30;
    /**
     * The constant CHECKSTYLE_POINTS.
     */
    public static final Integer CHECKSTYLE_POINTS = 10;
    /**
     * The constant TESTS_PATH.
     */
    public static final String TESTS_PATH = "input/";
    /**
     * The constant OUT_PATH.
     */
    public static final String OUT_PATH = "result/out_";
    /**
     * The constant REF_PATH.
     */
    public static final String REF_PATH = "ref/ref_";
    /**
     * The constant RESULT_PATH.
     */
    public static final String RESULT_PATH = "result";
    /**
     * The constant OUT_FILE.
     */
    public static final String OUT_FILE = "out.txt";
    /**
     * The constant GAME_START.
     */
    public static final String GAME_START = "game_start";
    /**
     * The constant MULTIPLE_GAMES_VALID.
     */
    public static final String MULTIPLE_GAMES_VALID = "multiple_games_valid";
    /**
     * The constant MULTIPLE_GAMES_INVALID.
     */
    public static final String MULTIPLE_GAMES_INVALID = "multiple_games_invalid";
    /**
     * The constant BIG_GAME.
     */
    public static final String BIG_GAME = "big_game";
    /**
     * The constant GAME_START_POINTS.
     */
    public static final Integer GAME_START_POINTS = 3;
    /**
     * The constant SINGLE_GAME_POINTS.
     */
    public static final Integer SINGLE_GAME_POINTS = 4;
    /**
     * The constant MULTIPLE_GAMES_VALID_POINTS.
     */
    public static final Integer MULTIPLE_GAMES_VALID_POINTS = 5;
    /**
     * The constant MULTIPLE_GAMES_INVALID_POINTS.
     */
    public static final Integer MULTIPLE_GAMES_INVALID_POINTS = 6;
    /**
     * The constant BIG_GAME_POINTS.
     */
    public static final Integer BIG_GAME_POINTS = 10;


    /**
     * The constant TANK_ERR.
     */
// add any constants you think you may use
    public static final String TANK_ERR = "Another minion must be attacked";
    /**
     * The constant FREEZE_ERR.
     */
    public static final String FREEZE_ERR = "The minion is frozen";
    /**
     * The constant WRONG_COORDINATES.
     */
    public static final String WRONG_COORDINATES = "Invalid coordinates";
    /**
     * The constant WRONG_MINION_ERR.
     */
    public static final String WRONG_MINION_ERR = "Minion not owned by the right player";
    /**
     * The constant ALREADY_USED_ERR.
     */
    public static final String ALREADY_USED_ERR = "Ability or attack already used in turn";
    /**
     * The constant MANA_ERR.
     */
    public static final String MANA_ERR = "Not enough mana";
    /**
     * The constant FRIEND_ERR.
     */
    public static final String FRIEND_ERR = "Can't use ability on a friend";
    /**
     * The constant ENEMY_ERR.
     */
    public static final String ENEMY_ERR = "Can't use ability on an enemy";
    /**
     * The constant NO_ABILITY_ERR.
     */
    public static final String NO_ABILITY_ERR = "Minion doesn't have special ability";
    /**
     * The constant FULL_LINE_ERR.
     */
    public static final String FULL_LINE_ERR = "Row is full";
    /**
     * The constant IS_ENV_CARD_ERR.
     */
    public static final String IS_ENV_CARD_ERR = "Bad type of card";
    /**
     * The constant CARD_NOT_IN_HAND.
     */
    public static final String CARD_NOT_IN_HAND = "Card not in hand";
    /**
     * The constant BAD_USE_OF_ENV.
     */
    public static final String BAD_USE_OF_ENV = "Can't place environment in your own field";
}
