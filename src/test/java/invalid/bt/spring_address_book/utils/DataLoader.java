package invalid.bt.spring_address_book.utils;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataLoader {

    private JdbcTemplate jdbcTemplate;

    public DataLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void init() throws IOException {
        execSql("starter_data.sql");
    }

    // Workaround for @Sql doing unwanted line parsing (problematic for starter_data.sql)
    private void execSql(String filename) throws IOException {
        final Path path = Path.of("./src/test/resources/", filename);
        final String content = Files.readString(path);
        jdbcTemplate.execute(content);
        return;
    }
}
