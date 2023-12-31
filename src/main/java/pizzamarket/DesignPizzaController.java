package pizzamarket;
import pizzamarket.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaorder")
public class DesignPizzaController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLPI", "Flour Pizza", Type.BACON),
                new Ingredient("COPI", "Corn Pizza", Type.CHEESE),
                new Ingredient("BFPI", "Beef Pizza", Type.BACON),
                new Ingredient("CEPI", "Cesar Pizza", Type.BASIS),
                new Ingredient("TMPI", "Tomatoes Pizza", Type.SAUCE),
                new Ingredient("CHPI", "Cheddar Pizza", Type.CHEESE),
                new Ingredient("JAPI", "Jack Pizza", Type.MUSHROOMS),
                new Ingredient("SLPI", "Salsa Pizza", Type.SAUCE),
                new Ingredient("CRPI", "Cream Pizza", Type.MUSHROOMS),
                new Ingredient("MAPI", "Madagascar Pizza", Type.BACON)
        );
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder order() {
        return new PizzaOrder();
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza(){
        return new Pizza();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients
                .stream()
                .filter(x->x.getType().equals(type))
                .collect(Collectors.toList());
    }
}