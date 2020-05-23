package invalid.bt.spring_address_book.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
public class CityForm {

    @Getter
    @Setter
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @Getter
    @Setter
    @NotNull
    @Size(min = 2, max = 2)
    private String zipCode;
}
