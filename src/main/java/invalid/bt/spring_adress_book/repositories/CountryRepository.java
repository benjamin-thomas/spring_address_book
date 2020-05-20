package invalid.bt.spring_adress_book.repositories;

import invalid.bt.spring_adress_book.entities.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Long> {
    Optional<Country> findByCode(String code);
//    @Query("SELECT c from Country AS c ORDER BY id DESC")
//    Country last();
}
