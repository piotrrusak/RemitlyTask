package com.example.task.service;

import com.example.task.repository.AddressRepository;
import com.example.task.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService implements AddressServiceInterface{

    private final AddressRepository addressRepository;

    @Override
    public Address findById(int id) {
        Optional<Address> result = this.addressRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Couldn't find bank by id");
        }
    }

    @Override
    public Address save(Address address) {
        return this.addressRepository.save(address);
    }

    @Override
    public void deleteById(int id) {
        this.addressRepository.deleteById(id);
    }

    public Address findByAddress(String address) {
        return this.addressRepository.findByAddress(address);
    }
}
