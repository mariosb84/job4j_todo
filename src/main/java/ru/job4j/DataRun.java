package ru.job4j;

import ru.job4j.model.Task;
import ru.job4j.model.User;

import java.time.ZoneId;
import java.util.List;

public class DataRun {

    public static void main(String[] args) {

        Task task = new Task();
        ZoneId pacific = ZoneId.of("US/Pacific");
        var time2 = task.getCreated().atZone(ZoneId.of("UTC+3")).withZoneSameInstant(pacific).toLocalDateTime();
        task.setCreated(time2);
       /* System.out.println(task.getCreated());*/

        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        task1.setUser(new User());
        task2.setUser(new User());
        task3.setUser(new User());
        task1.getUser().setTimezone("Africa/Abidjan  : Среднее время по Гринвичу");
        task2.getUser().setTimezone("Africa/Abidjan  : Среднее время по Гринвичу");
        task3.getUser().setTimezone("Africa/Abidjan  : Среднее время по Гринвичу");
        List<Task> taskList = List.of(task1, task2, task3);

       taskList.forEach(t -> t.setCreated(t.getCreated().atZone(ZoneId.of("UTC+3")).withZoneSameInstant(ZoneId.of("US/Pacific")).toLocalDateTime()));
        System.out.println(taskList);

        taskList.forEach(t -> t.setCreated(t.getCreated().
                atZone(ZoneId.of("UTC+3")).
                withZoneSameInstant(ZoneId.of(t.getUser().
                        getTimezone().split(":")[0].trim())).toLocalDateTime()));
        System.out.println(taskList);



    }
}
