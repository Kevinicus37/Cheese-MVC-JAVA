package org.launchcode.cheesemvcjava.controllers;

import org.launchcode.cheesemvcjava.models.Category;
import org.launchcode.cheesemvcjava.models.Cheese;
import org.launchcode.cheesemvcjava.models.Data.CheeseDao;
import org.launchcode.cheesemvcjava.models.Data.MenuDao;
import org.launchcode.cheesemvcjava.models.Menu;
import org.launchcode.cheesemvcjava.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    CheeseDao cheeseDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value = "add")
    public String add(Model model){

        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu newMenu, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(newMenu);

        return "redirect:";
    }

    @RequestMapping(value="view/{id}")
    public String viewMenu(Model model, @PathVariable int id){
        Menu menu = menuDao.findById(id).get();
        model.addAttribute("title", "Menu " + menu.getName());
        model.addAttribute("menu", menu);
        return "menu/view";

    }

    @RequestMapping(value="add-item/{id}")
    public String addItem(Model model, @PathVariable int id){
        Menu menu = menuDao.findById(id).get();
        AddMenuItemForm form = new AddMenuItemForm(menu, cheeseDao.findAll());
        model.addAttribute("form", form);
        model.addAttribute("title", "Add item to Menu:" + menu.getName());
        return "menu/add-item";
    }

    @RequestMapping(value="add-item", method=RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors){

        if (errors.hasErrors()){
            model.addAttribute("title", "Add item to menu: " + menuDao.findById(form.getMenuId()).get().getName());

            return "menu/add-item" + form.getMenuId();
        }
        Menu menu = menuDao.findById(form.getMenuId()).get();
        Cheese cheese = cheeseDao.findById(form.getCheeseId()).get();
        menu.addCheese(cheese);
        menuDao.save(menu);
        return "redirect:view/" + form.getMenuId();
    }
}
