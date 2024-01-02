package pizzamarket.web;

import org.springframework.web.bind.annotation.*;
import pizzamarket.Ingredient;
import pizzamarket.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import pizzamarket.Pizza;
import pizzamarket.PizzaOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController<pizzaOrder> {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLPI", "Flour Pizza", Type.BACON),
                new Ingredient("COPI", "Corn Pizza", Type.CHEESE),
                new Ingredient("BFPI", "Beef Pizza", Type.BACON),
                new Ingredient("CEPI", "Cesar Pizza", Type.BASE),
                new Ingredient("TMPI", "Tomatoes Pizza", Type.SAUCE),
                new Ingredient("CHPI", "Cheddar Pizza", Type.CHEESE),
                new Ingredient("JAPI", "Jack Pizza", Type.WRAP),
                new Ingredient("SLPI", "Salsa Pizza", Type.SAUCE),
                new Ingredient("CRPI", "Cream Pizza", Type.WRAP),
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
    public Pizza pizza() {
        return new Pizza();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processPizza(Pizza pizza, @ModelAttribute PizzaOrder pizzaOrder){
        pizzaOrder.addPizza(pizza);
        log.info("Processing pizza: {}", pizza);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}