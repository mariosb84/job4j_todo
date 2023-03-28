package ru.job4j.utilites;

import org.springframework.ui.Model;
import ru.job4j.model.Task;

import javax.servlet.http.HttpSession;

public final class Sessions {

    private Sessions() {

    }

    public static void taskSession(Model model, HttpSession session) {
        Task methodSessions = (Task) session.getAttribute("sessionTask");
        if (methodSessions == null) {
            methodSessions = new Task();
        }
        model.addAttribute("sessionTask", methodSessions);
    }

}
