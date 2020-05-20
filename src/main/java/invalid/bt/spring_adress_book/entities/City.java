package invalid.bt.spring_adress_book.entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long countryId;
    @Getter
    private String name;
    @Getter
    private String zipCode;

    protected City() {
    }

    public City(long countryId, String name, String zipCode) {
        this.countryId = countryId;
        this.name = name;
        this.zipCode = zipCode;
    }

}
