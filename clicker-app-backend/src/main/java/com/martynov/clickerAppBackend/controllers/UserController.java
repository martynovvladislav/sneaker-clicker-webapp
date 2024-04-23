package com.martynov.clickerAppBackend.controllers;

import com.martynov.clickerAppBackend.dtos.SneakerFromBoxDto;
import com.martynov.clickerAppBackend.dtos.UserDto;
import com.martynov.clickerAppBackend.services.BoxOpener;
import com.martynov.clickerAppBackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BoxOpener boxOpener;

    @PostMapping("/{id}")
    public ResponseEntity<Void> addUser(@PathVariable Long id) {
        userService.addUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(
            @RequestBody UserDto userDto
    ) {
        userService.updateUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(
            @PathVariable Long id
    ) {
        UserDto userDto = userService.getUser(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/openbox")
    public ResponseEntity<SneakerFromBoxDto> openUserBox(
            @PathVariable Long id
    ) {
        SneakerFromBoxDto sneaker = boxOpener.openBox(id);
        return new ResponseEntity<>(sneaker, HttpStatus.OK);
    }
}
