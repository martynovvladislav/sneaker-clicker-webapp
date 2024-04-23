package com.martynov.clickerAppBackend.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sneaker", schema = "public")
@Getter
@Setter
public class Sneaker {
    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "drop_chance")
    Float dropChance;

    @Column(name = "image_path")
    String imagePath;

    @ManyToMany(mappedBy = "userSneakers")
    protected List<User> users;
}
