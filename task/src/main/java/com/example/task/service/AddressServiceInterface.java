package com.example.task.service;

import com.example.task.entity.Address;

import java.util.List;

public interface AddressServiceInterface {

    public Address findById(int id);

    public Address save(Address address);

    public void deleteById(int id);

}
