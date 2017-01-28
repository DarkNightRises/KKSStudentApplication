package majorproject.kone.in.collegebudy.Utility;

/**
 * Created by kartikey on 13/01/17.
 */

        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

/**
 * Created by batra on 28-12-2016.
 */
public class Validation {
    private static Pattern pattern;
    private static Matcher matcher;
    private static String PHONE_PATTERN1 = "\\(\\d{3}\\)-\\d{3}-\\d{4}";
    private static String PHONE_PATTERN2 = "\\d{10}";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String USERNAME_PATTERN = "String regex = \"[a-zA-Z0-9\\\\._\\\\-]{3,}\";";

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String LETTERS_PATTERN = "[a-zA-Z_]+";
    private static final String NUMBERS_PATTERN = "[0-9]+";
    private static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]*$";
    private static final String SPECIAL_PATTERN = "[$&+,:;=?@#|]";


    public static boolean emailValidator(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean phoneValidator(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches(PHONE_PATTERN1)) return true;
            //validating phone number where area code is in braces ()
        else if (phoneNo.matches(PHONE_PATTERN2)) return true;
            //return false if nothing matches the input
        else return false;
    }

    public static boolean usernameValidator(String username) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean passwordValidator(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
        // At least 8 chars
        //Contains at least one digit
        //Contains at least one lower alpha char and one upper alpha char
        //Contains at least one char within a set of special chars (@#%$^ etc.)
        //Does not contain space, tab, etc.
    }

    public static boolean letterValidator(String string) {
        pattern = Pattern.compile(LETTERS_PATTERN);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean numberValidator(String number) {
        pattern = Pattern.compile(NUMBERS_PATTERN);
        matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public static boolean alphanummericValidator(String alphanumeric) {
        pattern = Pattern.compile(ALPHANUMERIC_PATTERN);
        matcher = pattern.matcher(alphanumeric);
        return matcher.matches();
    }

    public static boolean specialcharacterValidator(String special)
    {
        pattern = Pattern.compile(SPECIAL_PATTERN);
        matcher = pattern.matcher(special);
        return matcher.matches();
    }

}
