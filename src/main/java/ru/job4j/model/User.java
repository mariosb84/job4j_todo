package ru.job4j.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "todo_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    @EqualsAndHashCode.Include
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
    @NonNull
    @Setter
    @Getter
    @Column(name = "user_zone")
    private String timezone;

    }

