package org.launchcode.cheesemvcjava.controllers;

import org.launchcode.cheesemvcjava.models.Category;
import org.launchcode.cheesemvcjava.models.Data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model){
        Iterable<Category> categories = categoryDao.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("title", "Categories");
        return "category/index";
    }

    @RequestMapping(value = "add")
    public String add(Model model){

        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category());
        return "category/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category newCategory, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Category");
            return "category/add";
        }

        categoryDao.save(newCategory);

        return "redirect:";
    }
}
