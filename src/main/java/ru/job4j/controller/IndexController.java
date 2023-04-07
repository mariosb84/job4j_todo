package ru.job4j.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.service.TaskService;

@ThreadSafe
@Controller
public class IndexController {

    private final TaskService taskService;

    public IndexController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public String index(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "list";
    }

    @GetMapping("/listCompleted")
    public String indexCompleted(Model model) {
        model.addAttribute("tasks", taskService.findAllByCondition(true));
        return "list";
    }

    @GetMapping("/listNotCompleted")
    public String indexNotCompleted(Model model) {
        model.addAttribute("tasks", taskService.findAllByCondition(false));
        return "list";
    }

}
