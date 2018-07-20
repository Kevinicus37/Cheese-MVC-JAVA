package org.launchcode.cheesemvcjava.controllers;



import org.launchcode.cheesemvcjava.models.Cheese;
import org.launchcode.cheesemvcjava.models.Category;
import org.launchcode.cheesemvcjava.models.Data.CategoryDao;
import org.launchcode.cheesemvcjava.models.Data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by dogma on 6/7/2018.
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value="add")
    public String addCheese(Model model) {

        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseCategories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addCheese(@ModelAttribute @Valid Cheese newCheese,
                            Errors errors, @RequestParam int categoryId, Model model){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseCategories", categoryDao.findAll());
            return "cheese/add";
        }

        Category category = categoryDao.findById(categoryId).get();
        newCheese.setCategory(category);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value="remove")
    public String removeCheese(Model model){
        model.addAttribute("title", "Remove Cheese(s)");
        model.addAttribute("cheeses", cheeseDao.findAll());
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method=RequestMethod.POST)
    public String removeCheese(@RequestParam int[] cheeseIds){

        for (int id : cheeseIds) {
            cheeseDao.deleteById(id);
        }
        return "redirect:";
    }

    @RequestMapping(value="edit/{cheeseId}", method=RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId){
        Cheese editCheese = cheeseDao.findById(cheeseId).get();
        model.addAttribute("cheese", editCheese);
        model.addAttribute("cheeseCategories", categoryDao.findAll());
    return "cheese/edit";
    }

    @RequestMapping(value="edit", method=RequestMethod.POST)
    public String processEditForm(@RequestParam int categoryId,
                                  @RequestParam int cheeseId,
                                  @ModelAttribute @Valid Cheese alteredCheese,
                                  Errors errors, Model model){

        if (errors.hasErrors()){
            model.addAttribute("cheeseId", cheeseId);
            model.addAttribute("cheeseCategories", categoryDao.findAll());
            return "cheese/edit";
        }

        Cheese myCheese = cheeseDao.findById(cheeseId).get();
        myCheese.setName(alteredCheese.getName());
        myCheese.setDescription(alteredCheese.getDescription());
        myCheese.setRating(alteredCheese.getRating());
        myCheese.setCategory(categoryDao.findById(categoryId).get());
        cheeseDao.save(myCheese);
    return "redirect:";
    }

    @RequestMapping(value="category/{id}", method=RequestMethod.GET)
    public String getCategory(Model model, @PathVariable int id){
        Category cat = categoryDao.findById(id).get();
        model.addAttribute("cheeses", cat.getCheeses());
        return "cheese/index";
    }
}

