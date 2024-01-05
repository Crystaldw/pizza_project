package pizzamarket.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import pizzamarket.Ingredient;
import pizzamarket.Ingredient.Type;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("FLPI",
                new Ingredient("FLPI", "Flour Pizza", Ingredient.Type.BACON));
        ingredientMap.put("COPI",
                new Ingredient("COPI", "Corn Pizza", Ingredient.Type.CHEESE));
        ingredientMap.put("BEPI",
                new Ingredient("BFPI", "Beef Pizza", Ingredient.Type.BACON));
        ingredientMap.put("CEPI",
                new Ingredient("CEPI", "Cesar Pizza", Ingredient.Type.BASE));
        ingredientMap.put("TMPI",
                new Ingredient("TMPI", "Tomatoes Pizza", Ingredient.Type.SAUCE));
        ingredientMap.put("CHPI",
                new Ingredient("CHPI", "Cheddar Pizza", Ingredient.Type.CHEESE));
        ingredientMap.put("JAPI",
                new Ingredient("JAPI", "Jack Pizza", Ingredient.Type.WRAP));
        ingredientMap.put("SLPI",
                new Ingredient("SLPI", "Salsa Pizza", Ingredient.Type.SAUCE));
        ingredientMap.put("CRPI",
                new Ingredient("CRPI", "Cream Pizza", Ingredient.Type.WRAP));
        ingredientMap.put("MAPI",
                new Ingredient("MAPI", "Madagascar Pizza", Ingredient.Type.BACON));
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
