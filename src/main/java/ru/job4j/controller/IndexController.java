package ru.job4j.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.model.Task;
import ru.job4j.model.User;
import ru.job4j.service.TaskService;
import ru.job4j.utilites.Sessions;

import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

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
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", getTasksList(tasks));
        Sessions.userSession(model, session);
        return "/tasks/list";
    }

    @GetMapping("/listCompleted")
    public String indexCompleted(Model model, HttpSession session) {
        List<Task> tasks = taskService.findAllByCondition(true);
        model.addAttribute("tasks", getTasksList(tasks));
        Sessions.userSession(model, session);
        return "/tasks/list";
    }

    @GetMapping("/listNotCompleted")
    public String indexNotCompleted(Model model, HttpSession session) {
        List<Task> tasks = taskService.findAllByCondition(false);
        model.addAttribute("tasks", getTasksList(tasks));
        Sessions.userSession(model, session);
        return "/tasks/list";
    }

    private List<Task> getTasksList(List<Task> tasks) {
        tasks.forEach(
                t -> {
                if (t.getUser().getTimezone().equals("")) {
                    t.setCreated(t.getCreated().
                            atZone(ZoneId.of("UTC+3")).
                            withZoneSameInstant(ZoneId.of(String.valueOf(
                                    TimeZone.getDefault()))).toLocalDateTime());
        } else {
                    t.setCreated(t.getCreated().
                atZone(ZoneId.of("UTC+3")).
                withZoneSameInstant(ZoneId.of(t.getUser().
                        getTimezone().split(":")[0].trim())).toLocalDateTime());
                   }
                }
        );
        return tasks;
    }

}
