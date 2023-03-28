package ru.job4j.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String description;
    private LocalDateTime created = LocalDateTime.now();
    private  boolean done;

    public Task() {

    }

    public Task(int id, String description, LocalDateTime created, boolean done) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
    }

}
