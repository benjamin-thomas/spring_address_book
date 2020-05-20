package invalid.bt.spring_adress_book.repositories;

import invalid.bt.spring_adress_book.config.TestProfileResolver;
import invalid.bt.spring_adress_book.utils.DataLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest

// Mandatory if not using in memory test database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(resolver = TestProfileResolver.class)

class CityRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CityRepository cityRepository;

    @Test
    void assertSetup() throws IOException {
        new DataLoader(jdbcTemplate).init();
        assertEquals(12, cityRepository.count());
    }

}