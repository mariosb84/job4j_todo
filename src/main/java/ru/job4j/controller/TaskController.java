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

    @GetMapping("/taskDesc/{id}")
    public String getById(Model model, @PathVariable int id) {
      return findById(model, id, "/tasks/taskDesc");
    }

    @GetMapping("/formUpdateTask/{id}")
    public String formUpdateTask(Model model, @PathVariable int id) {
        return findById(model, id, "/tasks/editTask");
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
        var isUpdated = taskService.update(task, task.getId());
        if (!isUpdated) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/error404";
        }
        return "redirect:/taskList";
    }

   @GetMapping("/formAddPost")
    public String formAddTask(Model model) {
        model.addAttribute("task", new Task(0, "Заполните поле", LocalDateTime.now(), false));
        return "/tasks/addTask";
    }

    @PostMapping("/createTask")
    public String createPost(@ModelAttribute Task task) {
        taskService.add(task);
        return "redirect:/taskList";
    }

    @GetMapping("/deleteTask/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = taskService.delete(id);
        if (!isDeleted) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/error404";
        }
        return "redirect:/taskList";
    }

    @GetMapping("/doneTask{id}")
    public String doneTask(Model model, @PathVariable int id) {
        var taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задание с указанным идентификатором не найдено");
            return "errors/error404";
        }
        if (!taskOptional.get().isDone()) {
            taskOptional.get().setDone(true);
            model.addAttribute("task", taskOptional.get());
        }
        return "redirect:/taskList";
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
