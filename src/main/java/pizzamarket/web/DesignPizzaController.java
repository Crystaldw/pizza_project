package pizzamarket.web;

import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pizzamarket.Ingredient;
import pizzamarket.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import pizzamarket.Pizza;
import pizzamarket.PizzaOrder;
import pizzamarket.data.IngredientRepository;
import pizzamarket.data.JdbcIngredientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController{


    private final IngredientRepository ingredientRepo;

    public DesignPizzaController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients=ingredientRepo.findAll();
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLPI", "Flour Pizza", Type.BACON),
//                new Ingredient("COPI", "Corn Pizza", Type.CHEESE),
//                new Ingredient("BFPI", "Beef Pizza", Type.BACON),
//                new Ingredient("CEPI", "Cesar Pizza", Type.BASE),
//                new Ingredient("TMPI", "Tomatoes Pizza", Type.SAUCE),
//                new Ingredient("CHPI", "Cheddar Pizza", Type.CHEESE),
//                new Ingredient("JAPI", "Jack Pizza", Type.WRAP),
//                new Ingredient("SLPI", "Salsa Pizza", Type.SAUCE),
//                new Ingredient("CRPI", "Cream Pizza", Type.WRAP),
//                new Ingredient("MAPI", "Madagascar Pizza", Type.BACON)
//        );
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
    public String processPizza(@Valid Pizza pizza, Errors errors,
                               @ModelAttribute PizzaOrder pizzaOrder){
        if(errors.hasErrors()){
            return "design";
        }
        pizzaOrder.addPizza(pizza);
        log.info("Processing pizza: {}", pizza);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
           Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}