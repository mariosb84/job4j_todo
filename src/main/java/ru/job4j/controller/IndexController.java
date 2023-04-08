package ru.job4j.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.service.TaskService;

@ThreadSafe
@Controller
@RequestMapping("/tasks")
public class IndexController {

    private final TaskService taskService;

    public IndexController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public String index(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "/tasks/list";
    }

    @GetMapping("/listCompleted")
    public String indexCompleted(Model model) {
        model.addAttribute("tasks", taskService.findAllByCondition(true));
        return "/tasks/list";
    }

    @GetMapping("/listNotCompleted")
    public String indexNotCompleted(Model model) {
        model.addAttribute("tasks", taskService.findAllByCondition(false));
        return "/tasks/list";
    }

}
