package invalid.bt.spring_adress_book.repositories;

import invalid.bt.spring_adress_book.entities.City;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {
}
