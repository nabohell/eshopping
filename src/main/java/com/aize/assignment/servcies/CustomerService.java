package com.aize.assignment.servcies;

import com.aize.assignment.models.Customer;
import com.aize.assignment.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

   @Autowired
    CustomerRepo customerRepo;

   public Customer getCustomerByUserName(String username){
       return customerRepo.getById(username);
   }
}
