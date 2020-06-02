package invalid.bt.spring_address_book.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@ToString
public class Country {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // To read:
    //   I need to find ways to catch uniqueness violation, in a non-verbose way
    // https://stackoverflow.com/questions/9842672/best-practice-propagating-unique-violation-exceptions-to-ui
    // https://stackoverflow.com/questions/2109476/how-to-handle-dataintegrityviolationexception-in-spring/42422568#42422568
    @Getter
    @Setter
    @NotNull
    @Size(min=3, message = "Too short, this does not look like a real country name!")
    //@Column(nullable = false, unique = true)//<-- does not do anything useful
    private String name;

    @Getter
    @Setter
    @NotNull
    @Size(min = 2, max = 2)
    private String code;

    public Country() {
    }

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
