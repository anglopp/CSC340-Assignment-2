package com.csc340a2.CSC340a2.controller;

import com.csc340a2.CSC340a2.service.DogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dogs")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService){
        this.dogService = dogService;
    }

    // Base API endpoint, provides the link extensions for other GET requests
    @GetMapping("")
    public Map<String, Object> welcomeMessage() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to the Dogs API!");
        response.put("endpoints", List.of(
                "/random",
                "/breeds",
                "/breed/{breedName}"
        ));
        return response;
    }

    //Gets a random dog image
    @GetMapping("/random")
    public String getRandomDog() {
        return dogService.getRandomDogImage();
    }

    //Gets a list of all dog breeds
    @GetMapping("/breeds")
    public List<String> getAllBreeds() {
        return dogService.getAllBreeds();
    }

    //Gets a random image of the Specified Breed
    @GetMapping("/breed/{breedName}")
    public String getBreedImage(@PathVariable String breedName){
        return dogService.getBreedImage(breedName);
    }
}