package pizzamarket;

import java.util.ArrayList;
import java.util.List;

public class PizzaOrder {
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private List<Pizza>pizzas=new ArrayList<>();

    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }
}
