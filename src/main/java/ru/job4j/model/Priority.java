package ru.job4j.model;

import javax.persistence.*;

import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "priorities")
@AllArgsConstructor
@NoArgsConstructor
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NonNull
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
    private int position;
}
