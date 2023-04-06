package ru.job4j.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Task;
import ru.job4j.service.TaskService;

import java.time.LocalDateTime;

@ThreadSafe
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/desc/{id}")
    public String getById(Model model, @PathVariable int id) {
      return findById(model, id, "/tasks/desc");
    }

    @GetMapping("/formUpdate/{id}")
    public String formUpdate(Model model, @PathVariable int id) {
        return findById(model, id, "/tasks/edit");
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task) {
       taskService.update(task, task.getId());
        return "redirect:/list";
    }

   @GetMapping("/formAdd")
    public String formAdd(Model model) {
        model.addAttribute("task", new Task(0, "Заполните поле", LocalDateTime.now(), false));
        return "/tasks/add";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task) {
        taskService.add(task);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = taskService.delete(id);
        if (!isDeleted) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/error404";
        }
        return "redirect:/list";
    }

    @GetMapping("/done{id}")
    public String done(@PathVariable int id) {
       taskService.setDone(id);
        return "redirect:/list";
    }

    private  String findById(Model model, @PathVariable int id, String out) {
        var taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задание с указанным идентификатором не найдено");
            return "errors/error404";
        }
        model.addAttribute("task", taskOptional.get());
        return out;
    }


}
