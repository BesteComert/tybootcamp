package com.tybootcamp.ecomm.controllers;


import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.entities.Profile;
import com.tybootcamp.ecomm.entities.Seller;
import com.tybootcamp.ecomm.repositories.CustomerRepository;
import com.tybootcamp.ecomm.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository _customerRepository;

    @GetMapping(path = "/")
    public ResponseEntity<?> getCustomerById(@RequestParam(value = "id") long id){
        try {
            Customer customer = _customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            System.out.println("The customer with id " + id + " = " + customer.toString());
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>("There is not any seller with this name.", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(path = "/")
    public ResponseEntity<Customer> addNewSeller(@Valid @RequestBody Customer custom)
    {
        Customer customer = new Customer(custom.getAccountId());
        Profile profile = new Profile(customer, custom.getProfile().getFirstName(), custom.getProfile().getLastName()
                , custom.getProfile().getGender());
        customer.setProfile(profile);
        customer.getProfile().setWebsite(custom.getProfile().getWebsite());
        customer.getProfile().setAddress(custom.getProfile().getAddress());
        customer.getProfile().setEmailAddress(custom.getProfile().getEmailAddress());
        customer.getProfile().setBirthday(custom.getProfile().getBirthday());
        customer= _customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping(path = "/")
    public ResponseEntity<String> updateSeller(@Valid @RequestBody Customer customer)
    {
        Customer customerEntity = _customerRepository.findById(customer.getId()).orElse(null);
        if (customerEntity == null)
        {
            return new ResponseEntity<>("This seller doesn't exists.", HttpStatus.NOT_FOUND);
        }
        customerEntity.setAccountId(customer.getAccountId());
        customerEntity.getProfile().setFirstName(customer.getProfile().getFirstName());
        customerEntity.getProfile().setLastName(customer.getProfile().getLastName());
        customerEntity.getProfile().setWebsite(customer.getProfile().getWebsite());
        customerEntity.getProfile().setBirthday(customer.getProfile().getBirthday());
        customerEntity.getProfile().setAddress(customer.getProfile().getAddress());
        customerEntity.getProfile().setEmailAddress(customer.getProfile().getEmailAddress());
        customerEntity.getProfile().setGender(customer.getProfile().getGender());
        customerEntity = _customerRepository.save(customerEntity);
        System.out.println("__________________________________________________________________");
        System.out.println("The row of " + customerEntity.toString() + " updated");
        return new ResponseEntity<>("The seller updated", HttpStatus.OK);
    }

}
