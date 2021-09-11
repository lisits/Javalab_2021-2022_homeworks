import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        HikariConfig config = new HikariConfig();
        config.setPassword(properties.getProperty("db.password"));
        config.setUsername(properties.getProperty("db.user"));
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));

        DataSource dataSource = new HikariDataSource(config);

        EmailValidator emailValidator = new EmailValidatorRegexImpl();
        PasswordBlackList passwordBlackList = new PasswordBlackListFileImpl("passwords.txt");
        SignUpService service = new SignUpService(passwordBlackList, emailValidator);
        service.signUp("sidikovmarsel@gmail.com", "poppopit");

        PasswordBlackList passwordBlackList1 = new PasswordBlackListJDBCImpl(dataSource);
        SignUpService service1 = new SignUpService(passwordBlackList1, emailValidator);
        service1.signUp("sidikovmarsel@gmail.com", "qwerty007");
    }
}