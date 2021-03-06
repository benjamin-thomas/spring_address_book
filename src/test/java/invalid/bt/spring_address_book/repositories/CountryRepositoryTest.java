package invalid.bt.spring_address_book.repositories;

import invalid.bt.spring_address_book.config.TestProfileResolver;
import invalid.bt.spring_address_book.entities.Country;
import invalid.bt.spring_address_book.utils.DataLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest // default is transactional
@ActiveProfiles(resolver = TestProfileResolver.class)

// Mandatory if not using in memory test database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CountryRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CountryRepository countryRepository;

    @Test
    void lockPG() {
        String version = jdbcTemplate.queryForObject("SELECT version()", String.class);
        assertThat(version).contains("PostgreSQL 10.4 ");
    }

    @Test
    void uniqueNames() {
        Country country = new Country("France", "fr");
        Country duplicate = new Country("France", "fr");
        Exception exception = assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
                    countryRepository.save(country);
                    countryRepository.save(duplicate);
                }
        );
        // Caused by: org.postgresql.util.PSQLException: ERROR: duplicate key value violates unique constraint "countries_name_key"
        assertThat(exception.getMessage())
                .contains("could not execute statement")
                .contains("constraint [country_name_key]");
    }

    @Test
    void alwaysStartFromEmptyState() {
        assertThat(countryRepository.count()).isEqualTo(0);
    }

    @Test
    void insertion() throws IOException, SQLException {
        assertThat(countryRepository.count()).isEqualTo(0);
        new DataLoader(jdbcTemplate).init();
        assertThat(countryRepository.count()).isEqualTo(2);
        countryRepository.save(
                new Country("Italy", "it")
        );
        assertThat(countryRepository.count()).isEqualTo(3);
        final Country spain = countryRepository.findByCode("it").orElseThrow(
                () -> new AssertionError("Italy not found"));
        assertThat(spain.getName()).isEqualTo("Italy");
    }


    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(countryRepository).isNotNull();
    }
}
