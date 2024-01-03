package pizzamarket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //контроллер
public class HomeController {

    @GetMapping("/") //обрабатывает запросы с корневым путем
    public String home(){
        return "home"; //возвращает имя представления
    }
}