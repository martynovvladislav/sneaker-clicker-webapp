package com.martynov.clickerAppBackend.services;

import com.martynov.clickerAppBackend.domain.entities.Sneaker;
import com.martynov.clickerAppBackend.domain.entities.User;
import com.martynov.clickerAppBackend.domain.repositories.SneakerRepository;
import com.martynov.clickerAppBackend.domain.repositories.UserRepository;
import com.martynov.clickerAppBackend.dtos.SneakerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SneakerService {
    private final SneakerRepository sneakerRepository;
    private final UserRepository userRepository;

    public List<SneakerDto> getUserSneakers(Long userId) {
        if (userRepository.findUserById(userId).isEmpty()) {
            return new ArrayList<>();
        }
        User user = userRepository.findUserById(userId).get();
        List<SneakerDto> userSneakersList = sneakerRepository.findAll().stream()
                .map(sneaker -> new SneakerDto(
                        sneaker.getId(),
                        sneaker.getName(),
                        sneaker.getDescription(),
                        sneaker.getDropChance(),
                        sneaker.getImagePath(),
                        user.getUserSneakers().contains(sneaker)
                ))
                .toList();
        return userSneakersList;
    }

    public List<Sneaker> getAllSneakers() {
        return sneakerRepository.findAll();
    }
}
