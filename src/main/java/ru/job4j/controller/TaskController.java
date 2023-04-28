package ru.job4j.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Priority;
import ru.job4j.model.Task;
import ru.job4j.model.User;
import ru.job4j.service.TaskService;
import ru.job4j.utilites.Sessions;

import javax.servlet.http.HttpSession;
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
    public String getById(Model model, @PathVariable int id, HttpSession session) {
      return findById(model, id, "/tasks/desc", session);
    }

    @GetMapping("/formUpdate/{id}")
    public String formUpdate(Model model, @PathVariable int id, HttpSession session) {
        return findById(model, id, "/tasks/edit", session);
    }

    @PostMapping("/update")
    public String update(Model model, @ModelAttribute Task task) {
      var isUpdate = taskService.update(task, task.getId());
        if (!isUpdate) {
            model.addAttribute("message", "Задание с указанным идентификатором не найдено");
            return "errors/error404";
        }
        return "redirect:/tasks/list";
    }

   @GetMapping("/formAdd")
    public String formAdd(Model model, HttpSession session) {
        model.addAttribute("task", new Task(0, "Заполните поле", LocalDateTime.now(), false, new User(), new Priority()));
       Sessions.userSession(model, session);
        return "/tasks/add";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task) {
        taskService.add(task);
        return "redirect:/tasks/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id, HttpSession session) {
        var isDeleted = taskService.delete(id);
        if (!isDeleted) {
            model.addAttribute("message", "Задание с указанным идентификатором не найдено");
            return "errors/error404";
        }
        Sessions.userSession(model, session);
        return "redirect:/tasks/list";
    }

    @GetMapping("/done/{id}")
    public String done(Model model, @PathVariable int id, HttpSession session) {
      var isDone = taskService.setDone(id);
        if (!isDone) {
            model.addAttribute("message", "Задание с указанным идентификатором не найдено");
            return "errors/error404";
        }
        Sessions.userSession(model, session);
        return "redirect:/tasks/list";
    }

    private  String findById(Model model, @PathVariable int id, String out, HttpSession session) {
        var taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задание с указанным идентификатором не найдено");
            return "errors/error404";
        }
        model.addAttribute("task", taskOptional.get());
        Sessions.userSession(model, session);
        return out;
    }


}
