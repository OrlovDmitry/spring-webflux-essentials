package com.example.webflux.service;

import com.example.webflux.domain.Anime;
import com.example.webflux.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Flux<Anime> findAll() {
        return animeRepository.findAll ();
    }

    public Mono<Anime> findById(int id){
        return animeRepository.findById (id)
                .switchIfEmpty (monoResponseStatusNotFoundException ())
                .log ();
    }

    private Mono<Anime> monoResponseStatusNotFoundException() {
        return Mono.error (new ResponseStatusException (HttpStatus.NOT_FOUND, "Anime not found"));
    }
}
