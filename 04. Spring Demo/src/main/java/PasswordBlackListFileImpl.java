import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PasswordBlackListFileImpl implements PasswordBlackList {

    private String fileName;

    public PasswordBlackListFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean contains(String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().anyMatch(line -> line.equals(password));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
