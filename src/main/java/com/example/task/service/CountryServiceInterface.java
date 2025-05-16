package com.example.task.service;

import com.example.task.model.Country;

public interface CountryServiceInterface {

    public Country findById(String id);

    public Country save(Country country);

    public void deleteById(String id);

}
