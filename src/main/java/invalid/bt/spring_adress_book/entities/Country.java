package invalid.bt.spring_adress_book.entities;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // To read:
    //   I need to find ways to catch uniqueness violation, in a non-verbose way
    // https://stackoverflow.com/questions/9842672/best-practice-propagating-unique-violation-exceptions-to-ui
    // https://stackoverflow.com/questions/2109476/how-to-handle-dataintegrityviolationexception-in-spring/42422568#42422568
    @Getter
    //@Column(nullable = false, unique = true)//<-- does not do anything useful
    private String name;

    @Getter
    private String code;

    protected Country() {
    }

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
