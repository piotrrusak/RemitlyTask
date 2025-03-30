package com.example.task.service;

import com.example.task.dao.CountryRepository;
import com.example.task.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements CountryServiceInterface {

    private CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findById(String id) {
        Optional<Country> result = this.countryRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Couldn't find country");
        }
    }

    @Override
    public Country save(Country country) {
        return this.countryRepository.save(country);
    }

    @Override
    public void deleteById(String id) {
        this.countryRepository.deleteById(id);
    }

    public Country findByCountryName(String countryName) {
        return this.countryRepository.findByCountryName(countryName);
    }

}
