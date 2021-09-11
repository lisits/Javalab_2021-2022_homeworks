import java.util.Arrays;
import java.util.List;

public class PasswordBlackListHardCodeImpl implements PasswordBlackList {

    private static List<String> blacklist = Arrays.asList("qwerty007", "qwerty008", "qwerty", "marsel");

    @Override
    public boolean contains(String password) {
        return blacklist.contains(password);
    }
}