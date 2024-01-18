/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sa.quotes.customers.interfaces;

import com.sa.quotes.customers.model.Customer;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 *
 * @author manuel
 */
public interface CustomerService extends BaseService<Customer, Integer>{
    
    public List<Customer> search (String filter) throws Exception;
    
    public Page<Customer> search (String filter, Pageable pageable) throws Exception;
   
    
}
