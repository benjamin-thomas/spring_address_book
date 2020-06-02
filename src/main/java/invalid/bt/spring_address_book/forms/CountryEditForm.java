package invalid.bt.spring_address_book.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
public class CountryEditForm {

    @NotNull
    @NotEmpty
    @Size(min = 3, message = "Too damn short, this does not look like a real country name!")
    @Getter
    @Setter
    public String name;

}
