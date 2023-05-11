package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    @EqualsAndHashCode.Include
    private int id;
    @NonNull
    @Setter
    @Getter
    private  String description;
    @NonNull
    @Setter
    @Getter
    private LocalDateTime created = LocalDateTime.now();
    @Setter
    @Getter
    private  boolean done;
    @ManyToOne
    @JoinColumn(name = "user_id ")
    @NonNull
    @Setter
    @Getter
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    @NonNull
    @Setter
    @Getter
    private Priority priority;
    @Setter
    @Getter
    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = { @JoinColumn(name = "task_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private List<Category> participates = new ArrayList<>();
}
