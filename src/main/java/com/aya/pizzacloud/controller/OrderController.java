package com.aya.pizzacloud.controller;

import com.aya.pizzacloud.exception.PizzaNotFoundException;
import com.aya.pizzacloud.model.Pizza;
import com.aya.pizzacloud.model.PizzaOrder;
import com.aya.pizzacloud.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final PizzaRepository pizzaRepository;

    public OrderController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/current")
    public String orderForm(@RequestParam UUID pizzaId, Model model) {

        PizzaOrder pizzaOrder = new PizzaOrder();

        // Fix the getPizza method below in line 49.
        pizzaOrder.setPizza(getPizza(pizzaId));

        model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }

    @PostMapping("/{pizzaId}")
    public String processOrder(@PathVariable("pizzaId") UUID pizzaId, PizzaOrder pizzaOrder) {

        // Save the order

        pizzaOrder.setPizza(getPizza(pizzaId));
        return "redirect:/home";
    }

    //TODO
    private Pizza getPizza(UUID pizzaId) {
        // Get the pizza from repository based on it's id
        return pizzaRepository.readAll().stream().filter(p->p.getId().equals(pizzaId))
                .findFirst().orElseThrow(()->new PizzaNotFoundException("Pizza not found!"));
    }

}
