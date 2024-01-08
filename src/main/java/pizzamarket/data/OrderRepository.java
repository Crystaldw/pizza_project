package pizzamarket.data;

import pizzamarket.PizzaOrder;

public interface OrderRepository {
    PizzaOrder save(PizzaOrder order);
}
