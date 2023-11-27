package com.example.ffmjava232rickmortyapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rick-and-marty")
public class RickAndMortyController {


    @GetMapping()
    public List<RickAndMortyCharacters> getCharacters() {
        List<RickAndMortyCharacters> characters = WebClient.create()
                .get()
                .uri("https://rickandmortyapi.com/api/character")
                .retrieve()
                .bodyToFlux(RickAndMortyCharacters.class)
                .collectList()
                .block();

        return characters;
    }

//    @GetMapping("/{id}")
//    public RickAndMortyCharacters getCharacterById(@PathVariable int id) {
//
//        return WebClient.create()
//                .get()
//                .uri("https://rickandmortyapi.com/api/character/" + id )
//                .retrieve()
//                .bodyToMono(RickAndMortyCharacters.class)
//                .block();
//
//    } .getBody();

    @GetMapping("/{id}")
    public RickAndMortyResults getCharacterById(@PathVariable int id) {
        RickAndMortyResults character = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://rickandmortyapi.com/api/character/" + id)
                        .retrieve()
                        .toEntity(RickAndMortyResults.class)
                        .block()
        ).getBody();

        return character;
    }

}
