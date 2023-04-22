package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

}
