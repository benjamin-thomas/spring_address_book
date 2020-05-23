package invalid.bt.spring_address_book.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private long countryId;

    @Getter
    @Setter // for form submit
    private String name;

    @Getter
    @Setter // for form submit
    private String zipCode;

    public City() {
    }

    public City(long countryId, String name, String zipCode) {
        this.countryId = countryId;
        this.name = name;
        this.zipCode = zipCode;
    }

}
