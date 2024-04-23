package com.martynov.clickerAppBackend.controllers;

import com.martynov.clickerAppBackend.dtos.SneakerDto;
import com.martynov.clickerAppBackend.services.SneakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sneakers")
@RequiredArgsConstructor
public class SneakerController {
    private final SneakerService sneakerService;

    @GetMapping()
    public ResponseEntity<List<SneakerDto>> getSneakers(
            @RequestParam("user_id") Long userId
    ) {
        return new ResponseEntity<>(
                sneakerService.getUserSneakers(userId),
                HttpStatus.OK
        );
    }
}
