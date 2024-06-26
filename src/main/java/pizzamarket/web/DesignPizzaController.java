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