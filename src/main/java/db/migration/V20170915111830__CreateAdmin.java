package db.migration;

import com.incamp.mhs.user.User;
import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;

public class V20170915111830__CreateAdmin implements JdbcMigration {

    @Override
    public void migrate(Connection connection) throws Exception {
        User user = new User();
        user.setPassword("admin");
        user.setEmail("admin@admin.com");
        user.setFullName("admin");
        user.setUsername("admin");
        MigrationUserService.create(user);
    }
}
