import java.util.StringJoiner;

public class Password {
    private Integer id;
    private String password;

    public Password(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Password.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("password='" + password + "'")
                .toString();
    }
}
