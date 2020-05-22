package invalid.bt.spring_address_book.repositories;

import invalid.bt.spring_address_book.entities.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
// Generates URLs like:
//   - http://localhost:8080/cities/search/findByName?name=Paris
//   - http://localhost:8080/cities/1
// As well as POST/PUT/PATCH actions
// Note: … In fact, you need not even annotate interface if it is top-level and visible
public interface CityRepository extends PagingAndSortingRepository<City, Long> {
    public Optional<City> findByName(String name);
}