package pizzamarket;

import lombok.Data;

import java.lang.reflect.Type;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        BASIS, CHEESE, SAUCE, BACON, MUSHROOMS
    }
}
