package com.example.task.service;

import com.example.task.model.Address;

public interface AddressServiceInterface {

    public Address findById(int id);

    public Address save(Address address);

    public void deleteById(int id);

}
