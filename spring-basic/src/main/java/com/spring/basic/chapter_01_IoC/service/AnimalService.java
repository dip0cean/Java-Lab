package com.spring.basic.chapter_01_IoC.service;

import com.spring.basic.chapter_01_IoC.repository.AnimalRepository;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository dogRepositoryImpl) {
        this.animalRepository = dogRepositoryImpl;
    }
}
