package com.example.task.service;

import com.example.task.entity.Country;

import java.util.List;

public interface CountryServiceInterface {

    public Country findById(String id);

    public Country save(Country country);

    public void deleteById(String id);

}
