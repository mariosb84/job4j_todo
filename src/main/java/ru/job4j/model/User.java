package ru.job4j.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "todo_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private int id;
    @NonNull
    @Setter
    @Getter
    private String name;
    @NonNull
    @Setter
    @Getter
    private String login;
    @NonNull
    @Setter
    @Getter
    private String password;

    }
