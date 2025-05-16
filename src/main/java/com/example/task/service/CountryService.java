package com.example.task.service;

import com.example.task.repository.CountryRepository;
import com.example.task.model.Country;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService implements CountryServiceInterface {

    private final CountryRepository countryRepository;

    @Override
    public Country findById(String id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find country"));
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void deleteById(String id) {
        countryRepository.deleteById(id);
    }

    public Country findByCountryName(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }
}
