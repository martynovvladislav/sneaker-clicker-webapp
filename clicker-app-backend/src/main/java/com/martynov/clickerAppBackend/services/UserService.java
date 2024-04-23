package com.martynov.clickerAppBackend.services;

import com.martynov.clickerAppBackend.domain.entities.Sneaker;
import com.martynov.clickerAppBackend.domain.entities.User;
import com.martynov.clickerAppBackend.domain.repositories.SneakerRepository;
import com.martynov.clickerAppBackend.domain.repositories.UserRepository;
import com.martynov.clickerAppBackend.dtos.UserDto;
import com.martynov.clickerAppBackend.exceptions.UserAlreadyExistException;
import com.martynov.clickerAppBackend.exceptions.UserDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SneakerRepository sneakerRepository;

    public void addUser(Long id) {
        if (userRepository.findUserById(id).isPresent()) {
            throw new UserAlreadyExistException();
        }
        User user = new User();
        user.setId(id);
        user.setCoinsAmount(0L);
        user.setBoxesAmount(0L);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (userRepository.findUserById(id).isEmpty()) {
            throw new UserDoesNotExistException();
        }
        User user = userRepository.findUserById(id).get();
        user.eraseConnections();
        userRepository.deleteById(id);
    }

    public void updateUser(UserDto userDto) {
        if (userRepository.findUserById(userDto.getId()).isEmpty()) {
            throw new UserDoesNotExistException();
        }
        User user = userRepository.findUserById(userDto.getId()).get();
        user.setCoinsAmount(userDto.getCoinsAmount());
        user.setBoxesAmount(userDto.getBoxesAmount());
        userRepository.save(user);
    }

    public UserDto getUser(Long id) {
        if (userRepository.findUserById(id).isEmpty()) {
            throw new UserDoesNotExistException();
        }
        User user = userRepository.findUserById(id).get();
        return new UserDto(
                user.getId(),
                user.getCoinsAmount(),
                user.getBoxesAmount()
        );
    }

    public boolean addSneaker(Long userId, Long sneakerId) {
        if (userRepository.findUserById(userId).isEmpty() || sneakerRepository.findById(sneakerId).isEmpty()) {
            return false;
        }
        User user = userRepository.findUserById(userId).get();
        if (user.getUserSneakers().stream().map(Sneaker::getId).toList().contains(sneakerId)) {
            return false;
        }
        Sneaker sneaker = sneakerRepository.findById(sneakerId).get();
        user.userSneakers.add(sneaker);
        userRepository.save(user);
        return true;
    }
}
