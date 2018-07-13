package org.launchcode.cheesemvcjava.controllers;



import org.launchcode.cheesemvcjava.models.Cheese;
import org.launchcode.cheesemvcjava.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.launchcode.cheesemvcjava.models.CheeseData;

import javax.validation.Valid;

/**
 * Created by dogma on 6/7/2018.
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {



    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value="add")
    public String addCheese(Model model) {

        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addCheese(@ModelAttribute  @Valid Cheese newCheese,
                            Errors errors, Model model){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }

        CheeseData.add(newCheese);
        return "redirect:";
    }

    @RequestMapping(value="remove")
    public String removeCheese(Model model){
        model.addAttribute("title", "Remove Cheese(s)");
        model.addAttribute("cheeses", CheeseData.getAll());
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method=RequestMethod.POST)
    public String removeCheese(@RequestParam int[] cheeseIds){

        for (int id : cheeseIds) {
            CheeseData.remove(id);
        }
        return "redirect:";
    }

    @RequestMapping(value="edit/{cheeseId}", method=RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId){
        Cheese editCheese = CheeseData.getById(cheeseId);
        model.addAttribute("cheese", editCheese);
        model.addAttribute("cheeseTypes", CheeseType.values());
    return "cheese/edit";
    }

    @RequestMapping(value="edit", method=RequestMethod.POST)
    public String processEditForm(@RequestParam int cheeseId, @ModelAttribute @Valid Cheese alteredCheese,
                                  Errors errors, Model model){

        if (errors.hasErrors()){
            Cheese editCheese = CheeseData.getById(cheeseId);
            model.addAttribute("cheese", editCheese);
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "edit/" + cheeseId;
        }
        Cheese myCheese = CheeseData.getById(cheeseId);
        myCheese.setName(alteredCheese.getName());
        myCheese.setDescription(alteredCheese.getDescription());
        myCheese.setRating(alteredCheese.getRating());
        myCheese.setType(alteredCheese.getType());
    return "redirect:";
    }
}

