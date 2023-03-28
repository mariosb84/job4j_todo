package ru.job4j.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.Task;
import ru.job4j.service.TaskService;
import ru.job4j.utilites.Sessions;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@ThreadSafe
@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/taskList")
    public String index(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findAll());
        Sessions.taskSession(model, session);
        return "taskList";
    }

    @GetMapping("/taskListCompleted")
    public String indexCompleted(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findAllCompleted());
        Sessions.taskSession(model, session);
        return "taskList";
    }

    @GetMapping("/taskListNotCompleted")
    public String indexNotCompleted(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findAllNotCompleted());
        Sessions.taskSession(model, session);
        return "taskList";
    }

    @GetMapping("/taskDesc/{taskId}")
    public String getTaskDesc(Model model, @PathVariable("taskId") int id, HttpSession session) {
        model.addAttribute("task", taskService.findById(id));
        Sessions.taskSession(model, session);
        return "taskDesc";
    }

    @GetMapping("/editTask/{taskId}")
    public String formUpdateTask(Model model, @PathVariable("taskId") int id, HttpSession session) {
        model.addAttribute("task", taskService.findById(id));
        Sessions.taskSession(model, session);
        return "editTask";
    }

    @PostMapping("/editTask")
    public String editTask(@ModelAttribute Task task) {
        taskService.update(task, task.getId());
        return "redirect:/taskList";
    }


   @GetMapping("/formAddPost")
    public String formAddTask(Model model, HttpSession session) {
        model.addAttribute("task", new Task(0, "Заполните поле", LocalDateTime.now(), false));
       Sessions.taskSession(model, session);
        return "addTask";
    }

    @PostMapping("/createTask")
    public String createPost(@ModelAttribute Task task) {
        taskService.add(task);
        return "redirect:/taskList";
    }

    @PostMapping("/deleteTask{taskId}")
    public String deleteTask(@PathVariable("taskId") int id) {
        taskService.delete(id);
        return "redirect:/taskList";
    }

    @PostMapping("/doneTask{taskId}")
    public String doneTask(@PathVariable("taskId") int id) {
        taskService.findById(id).setDone(true);
        return "redirect:/taskList";
    }

}
