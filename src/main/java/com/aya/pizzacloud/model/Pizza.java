package com.aya.pizzacloud.model;

import com.aya.pizzacloud.enums.Cheese;
import com.aya.pizzacloud.enums.Sauce;
import com.aya.pizzacloud.enums.Topping;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Pizza {

    private UUID id;
    private List<Cheese> cheeseList;
    private List<Sauce> sauceList;
    private List<Topping> toppingList;

}
