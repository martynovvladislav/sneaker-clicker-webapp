package com.martynov.clickerAppBackend.services;

import com.martynov.clickerAppBackend.domain.entities.Sneaker;
import com.martynov.clickerAppBackend.dtos.SneakerFromBoxDto;
import com.martynov.clickerAppBackend.dtos.UserDto;
import com.martynov.clickerAppBackend.exceptions.UserDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BoxOpener {
    private final UserService userService;
    private final SneakerService sneakerService;
    private static final Random RANDOM = new Random();

    public SneakerFromBoxDto openBox(Long userId) {
        UserDto user = userService.getUser(userId);
        if (user == null) {
            throw new UserDoesNotExistException();
        }

        user.setBoxesAmount(user.getBoxesAmount() - 1);

        List<Sneaker> sneakers = sneakerService.getAllSneakers();

        List<Sneaker> chooseList = new ArrayList<>();
        for (var sneaker : sneakers) {
            for (int i = 0; i < sneaker.getDropChance() * 10; i++) {
                chooseList.add(sneaker);
            }
        }
        Sneaker chosenSneaker = chooseList.get(RANDOM.nextInt(chooseList.size()));
        boolean isNew = userService.addSneaker(userId, chosenSneaker.getId());

        return new SneakerFromBoxDto(
                chosenSneaker.getId(),
                chosenSneaker.getName(),
                chosenSneaker.getDropChance(),
                chosenSneaker.getImagePath(),
                isNew
        );
    }
}
