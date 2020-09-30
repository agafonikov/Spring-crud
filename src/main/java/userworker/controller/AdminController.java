package userworker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import userworker.model.User;
import userworker.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String mainPage(ModelMap model){
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "user_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addGet(){
        ModelAndView modelAndView = new ModelAndView("user_form");
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.addObject("submit_url", "/admin/add");
        modelAndView.addObject("submit_text", "Add");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateGet(@ModelAttribute("id") long id){
        ModelAndView modelAndView = new ModelAndView("user_form");
        User user = userService.getById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("submit_url", "/admin/update");
        modelAndView.addObject("submit_text", "Update");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPost(@ModelAttribute("obj") @Validated User user, BindingResult result){
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("user_form");
            modelAndView.addObject("user", user);
            return modelAndView;
        }
        else {
            user.setRole("USER");
            userService.add(user);
            return new ModelAndView("redirect:/admin");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updatePost(@ModelAttribute("obj") @Validated User user, BindingResult result){
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("user_form");
            modelAndView.addObject("user", user);
            return modelAndView;
        }
        else {
            userService.update(user);
            return new ModelAndView("redirect:/admin");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteGet(@ModelAttribute("id") long id){
        User user = userService.getById(id);
        userService.delete(user);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping("/form")
    public String userForm(ModelMap model){
        return "user_form";
    }
}
