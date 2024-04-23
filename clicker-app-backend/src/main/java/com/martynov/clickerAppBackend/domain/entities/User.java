package com.martynov.clickerAppBackend.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user", schema = "public")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "coins_amount")
    Long coinsAmount;

    @Column(name = "boxes_amount")
    Long boxesAmount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_sneaker",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sneaker_id")
    )
    public List<Sneaker> userSneakers;

    public void eraseConnections() {
        for (var sneaker : userSneakers) {
            sneaker.users.remove(this);
        }
        userSneakers.clear();
    }
}

