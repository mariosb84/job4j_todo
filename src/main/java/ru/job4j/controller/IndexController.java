package ru.job4j.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.model.User;
import ru.job4j.service.TaskService;
import ru.job4j.utilites.Sessions;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
@RequestMapping("/tasks")
public class IndexController {

    private final TaskService taskService;

    public IndexController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public String index(Model model, HttpSession session) {
        User userDb = (User) session.getAttribute("user");
        taskService.setUser(userDb);
        model.addAttribute("tasks", taskService.findAll().stream().distinct().toList());
        Sessions.userSession(model, session);
        return "/tasks/list";
    }

    @GetMapping("/listCompleted")
    public String indexCompleted(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findAllByCondition(true).
                stream().distinct().toList());
        Sessions.userSession(model, session);
        return "/tasks/list";
    }

    @GetMapping("/listNotCompleted")
    public String indexNotCompleted(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findAllByCondition(false).
                stream().distinct().toList());
        Sessions.userSession(model, session);
        return "/tasks/list";
    }

}
