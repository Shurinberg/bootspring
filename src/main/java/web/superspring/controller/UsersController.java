package web.superspring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.superspring.model.User;
import web.superspring.service.UserServiceImpl;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UsersController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userServiceImpl.findAllUsers());
        return "users/users";
    }

    @GetMapping("/new")
    public String addNewUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";
        userServiceImpl.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, ModelMap model){
        model.addAttribute("user", userServiceImpl.showUser(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "users/edit";
        userServiceImpl.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/{id}")
    public String removeUser(@ModelAttribute("id") int id) {
        userServiceImpl.removeUser(id);
        return "redirect:/users";
    }

}