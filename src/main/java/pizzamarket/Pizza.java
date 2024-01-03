package pizzamarket;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
@Data
public class Pizza {
    @NotNull
    @Size(min = 5, message ="You must choose at least 1 ingredient")
    private String name;
    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
