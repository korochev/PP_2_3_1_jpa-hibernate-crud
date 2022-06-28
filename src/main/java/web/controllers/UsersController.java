package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.dao.UserDAOImpl;
import web.models.User;
import web.service.UserService;

@Controller
public class UsersController {

    private final UserService userSrv;

    @Autowired
    public UsersController(UserService userService) {
        this.userSrv = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userSrv.index());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("User", userSrv.show(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("User") User User) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("User") User User,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";

        userSrv.save(User);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("User", userSrv.show(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("User") User User, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "users/edit";

        userSrv.update(User);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userSrv.delete(id);
        return "redirect:/";
    }
}
