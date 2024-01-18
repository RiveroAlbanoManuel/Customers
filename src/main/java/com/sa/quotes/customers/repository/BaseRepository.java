/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sa.quotes.customers.repository;


import com.sa.quotes.customers.model.Base;
import com.sa.quotes.customers.model.Customer;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author manuel
 */
public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID>{

    public Customer save(Customer customer);
    
    
}
