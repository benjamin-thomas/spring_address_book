package invalid.bt.spring_address_book.repositories;

import invalid.bt.spring_address_book.config.TestProfileResolver;
import invalid.bt.spring_address_book.entities.City;
import invalid.bt.spring_address_book.entities.Country;
import invalid.bt.spring_address_book.utils.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest

// Mandatory if not using in memory test database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(resolver = TestProfileResolver.class)
class CityRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CityRepository cityRepository;

    @BeforeEach
    void loadData() throws IOException {
        new DataLoader(jdbcTemplate).init();
    }

    @Test
    void assertSetup() {
        assertEquals(12, cityRepository.count());
    }

    @Test
    void findParis() {
        final City paris = cityRepository.findByName("Paris").orElseThrow();
        assertEquals("Paris", paris.getName());
        assertEquals("75001", paris.getZipCode());
    }

    @Test
    void createCity() {
        assertEquals(12, cityRepository.count());
        final Country france = countryRepository.findByCode("fr").orElseThrow();

        final City marseille = new City(france.getId(), "Marseille", "13000");
        cityRepository.save(marseille);

        assertEquals(13, cityRepository.count());
    }


}
